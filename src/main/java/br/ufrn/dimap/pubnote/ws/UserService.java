package br.ufrn.dimap.pubnote.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Transaction;

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
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"username":"Lucas Farias de Oliveira", "password":"senhaTopSecreta", "useremail":"luksrn@gmail.com"}' http://localhost:8080/pubnote.server/rest/user/register  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/register")
	public Response save(User user){
		//criando os DAOs que acessam o BD
		userDAO = userFactory.createDAO();
		profileDAO = pfactory.createDAO();

		/** first we must verify if the profile already exists **/
		Profile profile = user.getUserprofile();
		
		Transaction tx = userDAO.beginTransaction();
		ProfileEntity profileEntity = profileDAO.load(profile.getId());
		
		if(profileEntity.getId() == 0){
			profileEntity = new ProfileEntity(profile);
			profileDAO.persist(profileEntity);
		}
		
		UserEntity entity = new UserEntity(user);
		entity.setUserprofile(profileEntity);
		userDAO.persist(entity);
		
		tx.commit();
		return Response.status(201).build();
	}
	
	/**
	 * curl -i  -H "Content-Type: application/json" -X POST -d '{"useremail":"ju@mail.com", "password":"1234556"}' http://localhost:8080/pubnote.server/rest/user/login
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/login")
	public User login(User user){
		User logado = null;
		userDAO = userFactory.createDAO();
		
		Transaction tx = userDAO.beginTransaction();
		UserEntity uentity = userDAO.loadByUsername(user.getUseremail());
		// TODO Query in database...
		if(user.getUseremail().equals(uentity.getUseremail()) 
				&& user.getPassword().equals(uentity.getPassword())){			
			logado = uentity.convertToUser();
		}
		
		tx.commit();
		return logado;
	}
	
	
	/**
	 * curl -i  -H "Content-Type: application/json" -X POST -d '{"id":"6"}' http://localhost:8080/pubnote.server/rest/user/getProfile
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/getProfile")
	public Profile getProfileOfUser(User user){
		Profile profile = null;
		userDAO = userFactory.createDAO();
		
		Transaction tx = userDAO.beginTransaction();
		UserEntity uentity = userDAO.load(user.getId());
		// TODO Query in database...
		profile = uentity.getUserprofile().convertToProfile();
		
		tx.commit();
		return profile;
	}
	
	

	/**
	 * curl -i  -H "Content-Type: application/json" -X POST -d '{"institution":"UFRN", "degree":"mestrando","location":"Macaiba", "aboutme":"Algo sobre mim"}' http://localhost:8080/pubnote.server/rest/user/saveProfile
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/saveProfile")
	public Response saveProfileOfUser(User user){
		userDAO = userFactory.createDAO();
		profileDAO = pfactory.createDAO();

		Profile profile = user.getUserprofile();

		Transaction tx = userDAO.beginTransaction();
		
		ProfileEntity profileEntity = profileDAO.load(profile.getId());
		
		//atualiza Profile no banco
		if(profileEntity.getId() != 0){
			profileDAO.update(profileEntity);
		}
		
		//atualiza o Profile no usuario
		UserEntity uentity = userDAO.load(user.getId());
		uentity.setUserprofile(profileEntity);
		
		userDAO.update(uentity);
		
		tx.commit();
		return Response.status(201).build();
	}
}
