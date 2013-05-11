package br.ufrn.dimap.pubnote.dao;

public class ProfileFactory implements DAOFactory {

	@Override
	public ProfileDAO createDAO() {
		return new ProfileDAO();
	}
	
}
