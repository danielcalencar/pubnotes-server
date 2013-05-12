package br.ufrn.dimap.pubnote.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.Article;
import br.ufrn.dimap.pubnote.domain.Evaluation;
import br.ufrn.dimap.pubnote.domain.EvaluationEntity;

/**
 * This is the DAO for Evaluation
 * @author daniel
 *
 */
public class EvaluationDAO extends DAO<EvaluationEntity> 
{

	private static final String EVALUATIONS_FROM_ARTICLE = 
			"from Evaluation e where e.article.title = :title and e.published = :published";
	private static final String ALL_EVALUATIONS = 
			"from Evaluation e ";
	private static final String EVALUATION_FROM_USER_ARTICLE = 
			"from Evaluation e where e.user.id = :id and e.article.title = :title";
	
	public void persist(EvaluationEntity obj) 
	{
		session.persist(obj);
	}
	
	public Evaluation[] getEvaluationsFromArticle(String title)
	{
		String hql = EVALUATIONS_FROM_ARTICLE;
		Query query = session.createQuery(hql);
		query.setParameter("title", title);
		query.setParameter("published", true);
		List<EvaluationEntity> evaluations = query.list();
		Evaluation[] evalArray = new Evaluation[evaluations.size()];
		for(int i = 0; i < evalArray.length; i++)
		{
			evalArray[i] = evaluations.get(i).convertToEvaluation();
		}
		
		return evalArray;
	}
	
	public Evaluation[] getAllEvaluations()
	{
		String hql = ALL_EVALUATIONS;
		Query query = session.createQuery(hql);
		List<EvaluationEntity> evaluations = query.list();
		Evaluation[] evalArray = new Evaluation[evaluations.size()];
		for(int i = 0; i < evalArray.length; i++)
		{
			evalArray[i] = evaluations.get(i).convertToEvaluation();
		}
		
		return evalArray;
	}
	
	public EvaluationEntity getEvaluationFromUserArticle(long userId, String article)
	{
		String hql = EVALUATION_FROM_USER_ARTICLE;
		Query query = session.createQuery(hql);
		query.setParameter("id", userId);
		query.setParameter("title", article);
		EvaluationEntity eval = (EvaluationEntity) query.uniqueResult();
		return eval;
	}

	@Override
	public EvaluationEntity load(long id)
	{
		EvaluationEntity evalEntity = (EvaluationEntity) session.load(EvaluationEntity.class, id);
		return evalEntity;
	}

	@Override
	public void update(EvaluationEntity obj) 
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
	public void persistOrUpdate(EvaluationEntity obj) 
	{
		session.saveOrUpdate(obj);
		
	}

}
