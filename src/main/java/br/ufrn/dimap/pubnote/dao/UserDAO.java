package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.EvaluationEntity;
import br.ufrn.dimap.pubnote.domain.UserEntity;

public class UserDAO extends DAO<UserEntity>{

	@Override
	public void persist(UserEntity obj) {
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
		
	}

	@Override
	public UserEntity load(long id) {
		Transaction tx = session.beginTransaction();
		UserEntity userEntity = (UserEntity) session.load(UserEntity.class, id);
		tx.commit();
		return userEntity;
	}

	@Override
	public void update(UserEntity obj) {
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
	}

}
