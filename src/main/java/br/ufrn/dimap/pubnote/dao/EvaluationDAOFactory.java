package br.ufrn.dimap.pubnote.dao;

public class EvaluationDAOFactory implements DAOFactory 
{

	public EvaluationDAO createDAO() 
	{
		return new EvaluationDAO();
	}

}
