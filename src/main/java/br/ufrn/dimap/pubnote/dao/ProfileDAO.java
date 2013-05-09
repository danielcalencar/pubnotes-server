package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.ProfileEntity;

public class ProfileDAO extends DAO<ProfileEntity>{

	@Override
	public void persist(ProfileEntity obj) {
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
	}

	@Override
	public ProfileEntity load(long id) {
		Transaction tx = session.beginTransaction();
		ProfileEntity pEntity = (ProfileEntity) session.load(ProfileEntity.class, id);
		tx.commit();
		return pEntity;
	}

	@Override
	public void update(ProfileEntity obj) {
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
	}
	
	

}
