package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.ArticleEntity;

/*
 * This class is responsbile for the ArticleEntity database operations
 */
public class ArticleDAO extends DAO<ArticleEntity> 
{

	@Override
	public void persist(ArticleEntity obj) {
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
	}

	@Override
	public ArticleEntity load(long id) {
		Transaction tx = session.beginTransaction();
		ArticleEntity entity = (ArticleEntity) session.load(ArticleEntity.class, id);
		tx.commit();
		return entity;
	}

	@Override
	public void update(ArticleEntity obj) {
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
	}


}
