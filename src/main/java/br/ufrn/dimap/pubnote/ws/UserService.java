package br.ufrn.dimap.pubnote.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.dimap.pubnote.domain.User;
import br.ufrn.dimap.pubnote.ws.responses.LoginFailure;

@Path("/user")
public class UserService {

	/**
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d '{"username":"Lucas Farias de Oliveira", "password":"senhaTopSecreta", "email":"luksrn@gmail.com"}' http://localhost:8080/pubnote.server/rest/user/register  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user){
		System.err.println("Recebendo request para " + user );
		return Response.status(201).build();
	}
	
	/**
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d '{"username":"luksrn", "password":"senhaTopSecreta"}' http://localhost:8080/pubnote.server/rest/user/login
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response login(User user){
		if( user.getUsername().equalsIgnoreCase("userTeste" ) ){
			return Response.ok().build();
		}
		LoginFailure loginFailure = new LoginFailure();
		loginFailure.setMessage("Invalid login or password.");		
		return Response.status(401).entity(loginFailure).build();
	}
}
