package br.ufrn.dimap.pubnote.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.dimap.pubnote.domain.User;
import br.ufrn.dimap.pubnote.ws.responses.LoginResponse;

@Path( "/user" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class UserService {

	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"username":"Lucas Farias de Oliveira", "password":"senhaTopSecreta", "email":"luksrn@gmail.com"}' http://localhost:8080/pubnote.server/rest/user/register  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/register")
	public Response createUser(User user){		
		return Response.status(201).build();
	}
	
	/**
	 * curl -i  -H "Content-Type: application/json" -X POST -d '{"username":"luksrn", "password":"senhaTopSecreta"}' http://localhost:8080/pubnote.server/rest/user/login
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/login")
	public Response login(User user){
		LoginResponse response = new LoginResponse();		
		// TODO Query in database...
		if( user.getUsername().equalsIgnoreCase("pubnote@mail.com" ) ){			
			response.setSuccess(true);
			return Response.ok().entity(response).build();
		}
		response.setSuccess(false);
		response.setMessage("Invalid login or password.");		
		return Response.ok().entity(response).build();
	}
}
