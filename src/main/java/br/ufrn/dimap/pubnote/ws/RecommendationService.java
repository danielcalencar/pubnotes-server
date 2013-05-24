package br.ufrn.dimap.pubnote.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.dao.RecommendationDAO;
import br.ufrn.dimap.pubnote.dao.RecommendationDAOFactory;
import br.ufrn.dimap.pubnote.dao.UserDAO;
import br.ufrn.dimap.pubnote.dao.UserDAOFactory;
import br.ufrn.dimap.pubnote.domain.Recommendation;
import br.ufrn.dimap.pubnote.domain.RecommendationEntity;
import br.ufrn.dimap.pubnote.domain.UserEntity;

@Path( "/recommendation" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class RecommendationService 
{
	RecommendationDAOFactory recFactory = new RecommendationDAOFactory();
	RecommendationDAO recDAO;
	UserDAOFactory userDAOFactory = new UserDAOFactory();
	UserDAO userDAO;
	
	@POST
	public Recommendation createRecommendation(Recommendation recommendation)
	{
		recDAO  = recFactory.createDAO();
		userDAO = userDAOFactory.createDAO();
		Transaction tx = recDAO.beginTransaction();
		UserEntity user = userDAO.load(recommendation.getIdUsuario());
		RecommendationEntity recEntity = new RecommendationEntity();
		recEntity.setUser_remetente(user);
		
		List<UserEntity> destinatarios = new ArrayList<UserEntity>();
		for(Long idDestinatario : recommendation.getIdDestinatarios())
		{
			UserEntity destinatario = userDAO.load(idDestinatario);
			destinatarios.add(destinatario);
		}
		recEntity.setUser_destinatarios(destinatarios);
		recEntity.setAuthors(recommendation.getAuthors());
		recEntity.setConference(recommendation.getConference());
		recEntity.setLink(recommendation.getLink());
		recEntity.setObs(recommendation.getObs());
		recEntity.setTitle(recommendation.getTitle());
		recDAO.persist(recEntity);
		recDAO.commit(tx);
		
		return recommendation;
	}
	
	//http://url?id=1
	@GET
	public Recommendation[] getRecommendation(@QueryParam("id") String id)
	{
		long idDestinatario = Long.valueOf(id);
		recDAO = recFactory.createDAO();
		List<RecommendationEntity> recommendations = recDAO.loadRecommendations(idDestinatario);
		Recommendation[] dtos = new Recommendation[recommendations.size()];
		
		for(int i = 0; i < dtos.length; i++ )
		{
			dtos[i] = recommendations.get(i).convertToRecommendation();
		}
		
		return dtos;
	}
}
