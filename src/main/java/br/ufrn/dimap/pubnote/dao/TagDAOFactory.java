package br.ufrn.dimap.pubnote.dao;

public class TagDAOFactory implements DAOFactory {

	@Override
	public TagDAO createDAO() {
		return new TagDAO();
	}

}
