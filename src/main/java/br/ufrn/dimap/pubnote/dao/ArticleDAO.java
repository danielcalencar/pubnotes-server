package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Query;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.ArticleEntity;

/*
 * This class is responsbile for the ArticleEntity database operations
 */
public class ArticleDAO extends DAO<ArticleEntity> 
{
	private final static String ARTICLE_BY_TITLE = "from article a where a.title = :title";

	@Override
	public void persist(ArticleEntity obj) {
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
	}

	@Override
	public ArticleEntity load(long id) {
		ArticleEntity entity = (ArticleEntity) session.load(ArticleEntity.class, id);
		return entity;
	}
	
	public ArticleEntity loadByTitle(String title) {
		Query query = session.createQuery(ARTICLE_BY_TITLE);
		query.setParameter("title", title);
		ArticleEntity entity = (ArticleEntity) query.uniqueResult();
		return entity;
	}

	@Override
	public void update(ArticleEntity obj) {
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
	}


}
