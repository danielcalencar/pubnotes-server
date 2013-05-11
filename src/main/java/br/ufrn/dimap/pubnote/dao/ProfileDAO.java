package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.EvaluationEntity;
import br.ufrn.dimap.pubnote.domain.ProfileEntity;

public class ProfileDAO extends DAO<ProfileEntity>{

	@Override
	public void persist(ProfileEntity obj) 
	{
		session.persist(obj);
	}

	@Override
	public ProfileEntity load(long id) 
	{
		ProfileEntity pEntity = (ProfileEntity) session.load(ProfileEntity.class, id);
		return pEntity;
	}

	@Override
	public void update(ProfileEntity obj) 
	{
		session.update(obj);
	}

	@Override
	public void commit(Transaction transaction) 
	{
		transaction.commit();
	}

	@Override
	public Transaction beginTransaction() 
	{
		return session.beginTransaction();
	}
	
	@Override
	public void persistOrUpdate(ProfileEntity obj) 
	{
		session.saveOrUpdate(obj);
		
	}
	
	

}
