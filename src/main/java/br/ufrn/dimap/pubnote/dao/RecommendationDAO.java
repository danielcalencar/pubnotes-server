package br.ufrn.dimap.pubnote.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import br.ufrn.dimap.pubnote.domain.Recommendation;
import br.ufrn.dimap.pubnote.domain.RecommendationEntity;
import br.ufrn.dimap.pubnote.domain.User;
import br.ufrn.dimap.pubnote.domain.UserEntity;

public class RecommendationDAO extends DAO<RecommendationEntity>{

	
	private final static String RECOMMENDATION_BY_DEST = "from recommendation r where r.user_destinatarios_id = :dest";
	
	@Override
	public void persist(RecommendationEntity obj) {
		session.persist(obj);		
	}

	@Override
	public RecommendationEntity load(long id) {
		RecommendationEntity entity = (RecommendationEntity) session.load(RecommendationEntity.class, id);
		return entity;
		
	}

	@Override
	public void update(RecommendationEntity obj) 
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
	public void persistOrUpdate(RecommendationEntity obj) 
	{
		session.saveOrUpdate(obj);
		
	}

	
	
	public Recommendation[] retrieveRecommendations(String text) {
		Query query = session.createQuery(RECOMMENDATION_BY_DEST);
		query.setParameter("dest", text + "%");

		List<RecommendationEntity> recs = query.list();
		Recommendation[] recArray = new Recommendation[recs.size()];
		for(int i = 0; i < recArray.length; i++)
		{
			recArray[i] = recs.get(i).convertToRecommendation();
		}
		
		return recArray;
	}
	
	public List<RecommendationEntity> loadRecommendations(long idDestinatario)
	{
		String projection = "rec.id, rec.obs, rec.title, rec.conference, rec.link, rec.authors";
		String hql = "from " + projection + " recommendation rec " +
				"inner join rec.user_destinatarios destinararios" +
				" where destinatarios.id = :idDestinatario";
		
		Query query = session.createQuery(hql);
		List<Object[]> results = query.list();
		
		List<RecommendationEntity> recommendations = new ArrayList<RecommendationEntity>();
		for(Object[] obj : results)
		{
			RecommendationEntity rec = new RecommendationEntity();
			rec.setId((Long) obj[0]);
			rec.setObs((String) obj[1]);
			rec.setTitle((String) obj[2]);
			rec.setConference((String) obj[3]);
			rec.setLink((String) obj[4]);
			rec.setAuthors((String) obj[5]);
			
			if(!recommendations.contains(rec))
			{
				recommendations.add(rec);
			}
		}
		
		return recommendations;
	}
	
}
