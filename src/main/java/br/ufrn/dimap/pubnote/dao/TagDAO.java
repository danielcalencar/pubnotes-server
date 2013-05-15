package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.TagEntity;
import br.ufrn.dimap.pubnote.domain.UserEntity;

public class TagDAO extends DAO<TagEntity>{

	@Override
	public void persist(TagEntity obj) {
		// TODO Auto-generated method stub
		session.persist(obj);
		
	}

	@Override
	public TagEntity load(long id) {
		TagEntity entity = (TagEntity) session.load(TagEntity.class, id);
		return entity;
	}

	@Override
	public void update(TagEntity obj) {
		session.update(obj);
	}

	@Override
	public void commit(Transaction transaction) {
		transaction.commit();
	}

	@Override
	public Transaction beginTransaction() {
		return session.beginTransaction();
	}

	@Override
	public void persistOrUpdate(TagEntity obj) {
		session.saveOrUpdate(obj);
	}

}
