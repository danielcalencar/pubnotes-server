package br.ufrn.dimap.pubnote.ws;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.dimap.pubnote.dao.EvaluationDAO;
import br.ufrn.dimap.pubnote.dao.EvaluationDAOFactory;
import br.ufrn.dimap.pubnote.domain.Article;
import br.ufrn.dimap.pubnote.domain.Evaluation;
import br.ufrn.dimap.pubnote.domain.EvaluationEntity;

@Path( "/evaluation" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class EvaluationService 
{
	/** dao utilized to do the operations of evaluation**/
	EvaluationDAO evalDao;
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"id_user":"1", "id_article":"1", "originality":"2.4", "contribution":"4.2", "relevance":"2.3", "readability":"4.6", "relatedWorks":"4.5", "reviewerFamiliarity":"2.4"}' http://localhost:8080/pubnote.server/rest/evaluation/  
	 * @param user
	 * @return
	 */	
	@POST
	public Response createEvaluation(Evaluation evaluation){		
		EvaluationDAOFactory factory = new EvaluationDAOFactory();
		evalDao = factory.createDAO();
		evalDao.persist(evaluation);
		
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
		long id = Long.valueOf(bundle.get("article"));
		EvaluationDAOFactory factory = new EvaluationDAOFactory();
		EvaluationDAO evalDao = factory.createDAO();
		Evaluation[] evaluations = evalDao.getEvaluationsFromArticle(id);
		return evaluations;
	}
}
