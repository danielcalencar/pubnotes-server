package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Query;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.EvaluationEntity;
import br.ufrn.dimap.pubnote.domain.UserEntity;

/*
 * This class is responsbile for the ArticleEntity database operations
 */
public class UserDAO extends DAO<UserEntity> 
{
	private final static String USER_BY_USERNAME = "from punotes_user u where u.username = :username";

	@Override
	public void persist(UserEntity obj) {
		session.persist(obj);
	}

	@Override
	public UserEntity load(long id) {
		UserEntity entity = (UserEntity) session.load(UserEntity.class, id);
		return entity;
	}
	
	public UserEntity loadByUsername(String username) {
		Query query = session.createQuery(USER_BY_USERNAME);
		query.setParameter("username", username);
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
