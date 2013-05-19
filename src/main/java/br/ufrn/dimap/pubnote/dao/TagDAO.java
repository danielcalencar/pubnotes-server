package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Query;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.TagEntity;
import br.ufrn.dimap.pubnote.domain.UserEntity;

public class TagDAO extends DAO<TagEntity>{
	
	private final static String TAG_BY_DESCRIPTION = "from tag t where t.description = :description";

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

	public TagEntity loadByUsername(String descricao) {
		Query query = session.createQuery(TAG_BY_DESCRIPTION);
		query.setParameter("description", descricao);
		TagEntity entity = (TagEntity) query.uniqueResult();
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
