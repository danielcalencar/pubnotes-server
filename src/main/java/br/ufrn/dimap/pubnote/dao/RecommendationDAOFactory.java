package br.ufrn.dimap.pubnote.dao;

public class RecommendationDAOFactory implements DAOFactory{
	
	public RecommendationDAO createDAO(){
		return new RecommendationDAO();
	}

}
