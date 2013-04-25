package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.Evaluation;

/**
 * This is the DAO for Evaluation
 * @author daniel
 *
 */
public class EvaluationDAO extends DAO<Evaluation> 
{

	public void persist(Evaluation obj) 
	{
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
	}

}
