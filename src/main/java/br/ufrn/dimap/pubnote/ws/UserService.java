package br.ufrn.dimap.pubnote.ws;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.ufrn.dimap.pubnote.domain.User;

@Path("/user")
public class UserService {

	/**
	 * curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d '{"username":"Lucas Farias de Oliveira", "password":"senhaTopSecreta", "email":"luksrn@gmail.com"}' http://localhost:8080/pubnote.server/rest/user/register  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/register")
	public Response createUser(User user){
		System.err.println("Recebendo request para " + user );
		return Response.status(201).build();
	}
}
