package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.TagEntity;
import br.ufrn.dimap.pubnote.domain.TagUser;
import br.ufrn.dimap.pubnote.domain.TagUserEntity;

public class TagUserDAO extends DAO<TagUserEntity> {

	@Override
	public void persist(TagUserEntity obj) {
		session.persist(obj);
		
	}

	@Override
	public TagUserEntity load(long id) {
		TagUserEntity entity = (TagUserEntity) session.load(TagUserEntity.class, id);
		return entity;
	}

	@Override
	public void update(TagUserEntity obj) {
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
	public void persistOrUpdate(TagUserEntity obj) {
		session.saveOrUpdate(obj);
	}

}
