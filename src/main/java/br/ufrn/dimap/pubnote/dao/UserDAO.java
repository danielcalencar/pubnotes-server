package br.ufrn.dimap.pubnote.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.Evaluation;
import br.ufrn.dimap.pubnote.domain.EvaluationEntity;
import br.ufrn.dimap.pubnote.domain.FriendEntity;
import br.ufrn.dimap.pubnote.domain.ProfileEntity;
import br.ufrn.dimap.pubnote.domain.User;
import br.ufrn.dimap.pubnote.domain.UserEntity;

/*
 * This class is responsbile for the ArticleEntity database operations
 */
public class UserDAO extends DAO<UserEntity> 
{
	private final static String USER_BY_USEREMAIL = "from pubnotes_user u where u.useremail = :useremail";
	private final static String PROFILE_BY_USERID = "from profile p, pubnotes_user u where p.id = :u.profile.id";
	private final static String USERS_BY_USERNAME = "from pubnotes_user u where u.username = :username";
	
	
	@Override
	public void persist(UserEntity obj) {
		session.persist(obj);
	}

	@Override
	public UserEntity load(long id) {
		UserEntity entity = (UserEntity) session.load(UserEntity.class, id);
		return entity;
	}
	
	public User[] retrieveUsers(String text) {
		Query query = session.createQuery(USERS_BY_USERNAME);
		query.setParameter("username", text);

		List<UserEntity> users = query.list();
		User[] userArray = new User[users.size()];
		for(int i = 0; i < userArray.length; i++)
		{
			userArray[i] = users.get(i).convertToUser();
		}
		
		return userArray;
	}

	public UserEntity loadByUsername(String useremail) {
		Query query = session.createQuery(USER_BY_USEREMAIL);
		query.setParameter("useremail", useremail);
		UserEntity entity = (UserEntity) query.uniqueResult();
		return entity;
	}
	
	public void update(UserEntity obj) {
		session.update(obj);
	}

	public void commit(Transaction transaction) {
		transaction.commit();
		
	}

	public Transaction beginTransaction() {
		return session.beginTransaction();
	}
	
	@Override
	public void persistOrUpdate(UserEntity obj) 
	{
		session.saveOrUpdate(obj);
		
	}
}
