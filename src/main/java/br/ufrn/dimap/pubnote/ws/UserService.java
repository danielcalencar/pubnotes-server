package br.ufrn.dimap.pubnote.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.dao.ProfileDAO;
import br.ufrn.dimap.pubnote.dao.ProfileFactory;
import br.ufrn.dimap.pubnote.dao.TagDAO;
import br.ufrn.dimap.pubnote.dao.TagDAOFactory;
import br.ufrn.dimap.pubnote.dao.TagUserDAO;
import br.ufrn.dimap.pubnote.dao.TagUserDAOFactory;
import br.ufrn.dimap.pubnote.dao.UserDAO;
import br.ufrn.dimap.pubnote.dao.UserDAOFactory;
import br.ufrn.dimap.pubnote.domain.Profile;
import br.ufrn.dimap.pubnote.domain.ProfileEntity;
import br.ufrn.dimap.pubnote.domain.Tag;
import br.ufrn.dimap.pubnote.domain.TagEntity;
import br.ufrn.dimap.pubnote.domain.TagUserEntity;
import br.ufrn.dimap.pubnote.domain.User;
import br.ufrn.dimap.pubnote.domain.UserEntity;

@Path( "/user" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class UserService {
	
	UserDAOFactory userFactory = new UserDAOFactory();
	//o profile tambem deve ser persistido
	ProfileFactory pfactory = new ProfileFactory();
	TagDAOFactory tfactory = new TagDAOFactory();
	TagUserDAOFactory tufactoty = new TagUserDAOFactory();
	UserDAO userDAO;
	ProfileDAO profileDAO;
	TagDAO tagDAO;
	TagUserDAO tagUserDAO;
	
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
			uentity.setOnsigned(true);
			userDAO.update(uentity);
			logado = uentity.convertToUser();
		}
		
		tx.commit();
		return logado;
	}
	
	
	/**
	 * curl -i  -H "Content-Type: application/json" -X POST -d '{"friends":[],"userprofile":{"aboutme":"","birthday":"21-11-1989","degree":"","email":"","facebook":"","gender":"","phone":"","institution":"UFRN-DIMAp","location":"","id":5},"username":"juliana","password":"1234556","tags":[],"useremail":"ju@mail.com","id":6,"onsigned":false}' http://localhost:8080/pubnote.server/rest/user/getProfile
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
	 * curl -i  -H "Content-Type: application/json" -X POST -d '{"friends":[],"userprofile":{"aboutme":"estudante","birthday":"21","degree":"mestre","email":"ju@mail.com","facebook":"juju","gender":"F","phone":"23345","institution":"ufrn","location":"natal","id":5},"username":"juliana","password":"1234556","tags":[],"useremail":"ju@mail.com","id":6,"onsigned":false}' http://localhost:8080/pubnote.server/rest/user/saveProfile
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/saveProfile")
	public Response saveProfileOfUser(User user){
		//criando os DAOs que acessam o BD
		userDAO = userFactory.createDAO();
		profileDAO = pfactory.createDAO();

		/** first we must verify if the profile already exists **/
		Profile profile = user.getUserprofile();
		
		Transaction tx = userDAO.beginTransaction();
		ProfileEntity profileEntity = profileDAO.load(profile.getId());
		
		if(profileEntity.getId() != 0){
			//profileEntity = new ProfileEntity(profile);
			profileEntity.updateValues(profile);
			profileDAO.update(profileEntity);
		}
		
		UserEntity uentity = userDAO.load(user.getId());
		uentity.setUserprofile(profileEntity);
		userDAO.update(uentity);
		
		tx.commit();
		return Response.status(201).build();
	}

	/**
	 * curl -i  -H "Content-Type: application/json" -X POST -d '{"id":"6"}' http://localhost:8080/pubnote.server/rest/user/addFriends
	 * 
	 * @param user
	 * @return
	 */
	@POST
	@Path("/addFriends")
	public Response addFriendsOfUser(User user)
	{	
			userDAO = userFactory.createDAO();
			tagDAO = tfactory.createDAO();
		
			Transaction tx = userDAO.beginTransaction();
			//pegando o usuario atual
			UserEntity entity = userDAO.load(user.getId());
			//agora vou adicionar os amiguinhos dele
			//for(User friend : user.getFriends())
			//{
				int index = user.getFriends().size() - 1;
				UserEntity userfriend = (UserEntity) userDAO.load(user.getFriends().get(index).getId());
				entity.getFriends().add(userfriend);
				userDAO.update(entity);
			//}
			tx.commit();
			return Response.status(201).build();
	}
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X GET -d '{"username":"juliana"}' http://localhost:8080/pubnote.server/rest/user/getPeople
	 * @param user
	 * @return
	 */	
	@GET
	@Path("/getPeople")
	public User[] retrieveUsers(@QueryParam("username") String username)
	{
		userDAO = userFactory.createDAO();
		//Transaction tx = evalDao.beginTransaction();
		User[] users = userDAO.retrieveUsers(username);
		//evalDao.commit(tx);
		return users;
	}
	
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"username":"juliana"}' http://localhost:8080/pubnote.server/rest/user/getFriends
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/getFriends")
	public User[] retrieveFriends(User user)
	{
		userDAO = userFactory.createDAO();
		Transaction tx = userDAO.beginTransaction();

		UserEntity entity = userDAO.load(user.getId());
		List<UserEntity> friends = entity.getFriends();
		
		User[] userArray = new User[friends.size()];
		if(friends.size() != 0){	
			for(int i = 0; i < userArray.length; i++)
			{
				userArray[i] = friends.get(i).convertToUser();
			}
		}
		
		userDAO.commit(tx);
		return userArray;
	}
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"id":"6"}' http://localhost:8080/pubnote.server/rest/user/addTag  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/addTag")
	public Response addTag(User user){
		//criando os DAOs que acessam o BD
		userDAO = userFactory.createDAO();
		tagDAO = tfactory.createDAO();
		
		/** first we must verify if the tag already exists **/
		Transaction tx = userDAO.beginTransaction();
		UserEntity entity = userDAO.load(user.getId());
		//for(Tag tag : user.getTags()){ 
			int index = user.getTags().size() - 1;
			TagEntity tentity = tagDAO.load(user.getTags().get(index).getId());
			if(tentity.getId() == 0){
				tentity = new TagEntity();
				tentity.setDescription(user.getTags().get(index).getDescription());
				tagDAO.persist(tentity);
			}else{
				tentity.setDescription(user.getTags().get(index).getDescription());
				tagDAO.update(tentity);
			}
			entity.getTags().add(tentity);
			userDAO.update(entity);
		//}
				
		tx.commit();
		return Response.status(201).build();
	}
	
	/**
	 * curl -i   -H "Content-Type: application/json" -X POST -d '{"id":"6"}' http://localhost:8080/pubnote.server/rest/user/addTag  
	 * @param user
	 * @return
	 */	
	@POST
	@Path("/addTagUser")
	public Response makeTagUser(User user){
		//criando os DAOs que acessam o BD
		userDAO = userFactory.createDAO();
		tagDAO = tfactory.createDAO();
		tagUserDAO = tufactoty.createDAO();
		
		/** first we must verify if the tag already exists **/
		Transaction tx = userDAO.beginTransaction();
		//usuario que foi marcado
		UserEntity entity = userDAO.load(user.getId());
		//pego a taguser que foi adicionada no cliente
		int index = user.getMarkedTags().size() - 1;
		System.out.println("Tam " + index);
		//pegando entidades User e Tag no banco
		TagEntity tu = tagDAO.loadByUsername(user.getMarkedTags().get(index).getTag().getDescription());
		UserEntity u = userDAO.load(user.getMarkedTags().get(index).getUser().getId());
		//entidade que representa a tag pelo qual o user foi marcado e quem o marcou
		//nao precisa ser persistida no banco
		
	
		TagUserEntity tuentity = tagUserDAO.load(user.getMarkedTags().get(index).getId());
		
		if(tuentity.getId() == 0){
			tuentity = new TagUserEntity();
			tuentity.setTag(tu);
			tuentity.setUser(u);
			tagUserDAO.persist(tuentity);
		}
		
		entity.getMarkedTags().add(tuentity);	
		
		userDAO.update(entity);
			
		tx.commit();
		return Response.status(201).build();
	}
}
