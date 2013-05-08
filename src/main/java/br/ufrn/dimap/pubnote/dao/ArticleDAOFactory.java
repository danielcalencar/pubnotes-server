package br.ufrn.dimap.pubnote.dao;

public class ArticleDAOFactory implements DAOFactory 
{

	public ArticleDAO createDAO() 
	{
		return new ArticleDAO();
	}

}
