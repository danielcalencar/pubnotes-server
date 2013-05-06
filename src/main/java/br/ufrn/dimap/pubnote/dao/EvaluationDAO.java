package br.ufrn.dimap.pubnote.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.Article;
import br.ufrn.dimap.pubnote.domain.Evaluation;
import br.ufrn.dimap.pubnote.domain.EvaluationEntity;

/**
 * This is the DAO for Evaluation
 * @author daniel
 *
 */
public class EvaluationDAO extends DAO<Evaluation> 
{

	private static final String EVALUATIONS_FROM_ARTICLE_QUERY = 
			"from Evaluation e where e.article.id = :id";
	private static final String ALL_EVALUATIONS = 
			"from Evaluation e ";
	
	public void persist(Evaluation obj) 
	{
		EvaluationEntity entity = new EvaluationEntity(obj);
		
		Transaction tx = session.beginTransaction();
		session.persist(entity);
		tx.commit();
	}
	
	public Evaluation[] getEvaluationsFromArticle(long id)
	{
		String hql = EVALUATIONS_FROM_ARTICLE_QUERY;
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
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

}
