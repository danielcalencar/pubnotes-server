package br.ufrn.dimap.pubnote.dao;

public class UserDAOFactory implements DAOFactory
{

	public UserDAO createDAO() {
		return new UserDAO();
	}
}
