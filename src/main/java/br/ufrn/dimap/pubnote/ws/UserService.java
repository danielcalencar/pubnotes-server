package br.ufrn.dimap.pubnote.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.dimap.pubnote.dao.ProfileDAO;
import br.ufrn.dimap.pubnote.dao.ProfileFactory;
import br.ufrn.dimap.pubnote.dao.UserDAO;
import br.ufrn.dimap.pubnote.dao.UserDAOFactory;
import br.ufrn.dimap.pubnote.domain.Article;
import br.ufrn.dimap.pubnote.domain.ArticleEntity;
import br.ufrn.dimap.pubnote.domain.Profile;
import br.ufrn.dimap.pubnote.domain.ProfileEntity;
import br.ufrn.dimap.pubnote.domain.User;
import br.ufrn.dimap.pubnote.domain.UserEntity;
import br.ufrn.dimap.pubnote.ws.responses.LoginResponse;

@Path( "/user" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class UserService {
	
	UserDAOFactory userFactory = new UserDAOFactory();
	//o profile tambem deve ser persistido
	ProfileFactory pfactory = new ProfileFactory();
	UserDAO userDAO;
	ProfileDAO profileDAO;
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"username":"Lucas Farias de Oliveira", "password":"senhaTopSecreta", "email":"luksrn@gmail.com"}' http://localhost:8080/pubnote.server/rest/user/register  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/save")
	public Response save(User user){
		//criando os DAOs que acessam o BD
		userDAO = userFactory.createDAO();
		profileDAO = pfactory.createDAO();

		/** first we must verify if the profile already exists **/
		Profile profile = user.getUserprofile();
		ProfileEntity profileEntity = profileDAO.load(profile.getId());
		
		if(profileEntity == null){
			profileEntity = new ProfileEntity(profile);
			profileDAO.persist(profileEntity);
		}
		
		UserEntity entity = new UserEntity(user);
		entity.setUserprofile(profileEntity);
		userDAO.persist(entity);
		
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
