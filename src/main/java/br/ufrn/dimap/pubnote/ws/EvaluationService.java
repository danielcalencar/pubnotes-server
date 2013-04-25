package br.ufrn.dimap.pubnote.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.dimap.pubnote.dao.EvaluationDAO;
import br.ufrn.dimap.pubnote.dao.EvaluationDAOFactory;
import br.ufrn.dimap.pubnote.domain.Evaluation;

@Path( "/user" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class EvaluationService 
{
	/** dao utilized to do the operations of evaluation**/
	EvaluationDAO evalDao;
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{}' http://localhost:8080/pubnote.server/rest/evaluation/register  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/register")
	public Response createEvaluation(Evaluation evaluation){		
		
		//TODO: complete functionality here
		EvaluationDAOFactory factory = new EvaluationDAOFactory();
		evalDao = factory.createDAO();
		evalDao.persist(evaluation);
		
		return Response.status(201).build();
	}	
}
