package br.ufrn.dimap.pubnote.dao;

public class TagUserDAOFactory implements DAOFactory{
	@Override
	public TagUserDAO createDAO() {
		return new TagUserDAO();
	}
}
