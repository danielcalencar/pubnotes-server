package br.ufrn.dimap.pubnote.ws;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Transaction;

import com.sun.xml.bind.v2.runtime.Name;

import br.ufrn.dimap.pubnote.dao.ArticleDAO;
import br.ufrn.dimap.pubnote.dao.ArticleDAOFactory;
import br.ufrn.dimap.pubnote.dao.EvaluationDAO;
import br.ufrn.dimap.pubnote.dao.EvaluationDAOFactory;
import br.ufrn.dimap.pubnote.dao.UserDAO;
import br.ufrn.dimap.pubnote.dao.UserDAOFactory;
import br.ufrn.dimap.pubnote.domain.Article;
import br.ufrn.dimap.pubnote.domain.ArticleEntity;
import br.ufrn.dimap.pubnote.domain.Evaluation;
import br.ufrn.dimap.pubnote.domain.EvaluationEntity;
import br.ufrn.dimap.pubnote.domain.User;
import br.ufrn.dimap.pubnote.domain.UserEntity;

@Path( "/evaluation" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class EvaluationService 
{
	/** dao utilized to do the operations of evaluation**/
	EvaluationDAOFactory evalFactory = new EvaluationDAOFactory();
	EvaluationDAO evalDao;
	ArticleDAOFactory articleFactory = new ArticleDAOFactory();
	ArticleDAO articleDao;
	UserDAOFactory userFactory = new UserDAOFactory();
	UserDAO userDao;
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"id_user":"1", "id_article":"1", "originality":"2.4", "contribution":"4.2", "relevance":"2.3", "readability":"4.6", "relatedWorks":"4.5", "reviewerFamiliarity":"2.4"}' http://localhost:8080/pubnote.server/rest/evaluation/  
	 * @param user
	 * @return
	 */	
	@POST
	public Response createEvaluation(Evaluation evaluation){		
		evalDao = evalFactory.createDAO();
		articleDao = articleFactory.createDAO();
		
		/** first we must verify if the article already exists **/
		Article article = evaluation.getArticle();
		User user = evaluation.getUser();
		Transaction tx = articleDao.beginTransaction();
		ArticleEntity articleEntity = articleDao.loadByTitle(article.getTitle());
		if(articleEntity == null)
		{
			/**in that case we must persist the article **/
			articleEntity = new ArticleEntity(article);
			articleDao.persist(articleEntity);
		}
		
		/** load the user from the database 
		 * the user already exists**/
		userDao = userFactory.createDAO();
		UserEntity userEntity = userDao.load(user.getId());
		
		/** lets persist the evaluation **/
		EvaluationEntity evalEntity = new EvaluationEntity(evaluation);
		evalEntity.setArticle(articleEntity);
		evalEntity.setUser(userEntity);
		evalDao.persist(evalEntity);
		
		/** now lets associate the evaluation with the article**/
		articleEntity.getEvaluations().add(evalEntity);
		articleDao.update(articleEntity);
		
		articleDao.commit(tx);
		return Response.status(201).build();
	}
	
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X GET -d <expressao aqui> http://localhost:8080/pubnote.server/rest/evaluation  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/evaluationsFromArticle")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public Evaluation[] retrieveEvaluations(Map<String,String> bundle)
	{
		String title = bundle.get("article");
		EvaluationDAOFactory factory = new EvaluationDAOFactory();
		EvaluationDAO evalDao = factory.createDAO();
		//Transaction tx = evalDao.beginTransaction();
		Evaluation[] evaluations = evalDao.getEvaluationsFromArticle(title);
		//evalDao.commit(tx);
		return evaluations;
	}
	
	@PUT
	public Response saveOrUpdateEvaluation(Evaluation evaluation){		
		evalDao = evalFactory.createDAO();
		articleDao = articleFactory.createDAO();
		
		/** first we must verify if the article already exists **/
		Article article = evaluation.getArticle();
		User user = evaluation.getUser();
		Transaction tx = articleDao.beginTransaction();
		ArticleEntity articleEntity = articleDao.loadByTitle(article.getTitle());
		if(articleEntity == null)
		{
			/**in that case we must persist the article **/
			articleEntity = new ArticleEntity(article);
			articleDao.persist(articleEntity);
		}
		
		/** load the user from the database 
		 * the user already exists**/
		userDao = userFactory.createDAO();
		UserEntity userEntity = userDao.load(user.getId());
		
		/** lets persist or update the evaluation **/
		EvaluationEntity evalEntity = (EvaluationEntity) evalDao.load(evaluation.getId());
		if(evalEntity == null)
		{
			evalEntity = new EvaluationEntity(evaluation);
			evalEntity.setArticle(articleEntity);
			evalEntity.setUser(userEntity);
			evalDao.persist(evalEntity);
			/** now lets associate the evaluation with the article**/
			articleEntity.getEvaluations().add(evalEntity);
			articleDao.update(articleEntity);
		}
		else
		{
			evalDao.update(evalEntity);
		}
		
		articleDao.commit(tx);
		return Response.status(201).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Evaluation getEvaluationInCourse(@QueryParam("id") String id)
	{
		long userId = Long.valueOf(id);
		evalDao = evalFactory.createDAO();
		EvaluationEntity evalEntity = evalDao.getEvaluationFromUser(userId);
		Evaluation eval = evalEntity.convertToEvaluation();
		return eval;
	}
}
