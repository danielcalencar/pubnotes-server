package br.ufrn.dimap.pubnote.dao;

/**
 * Every DAOFactory should implement this class
 * @author daniel
 *
 */
public interface DAOFactory 
{
	public DAO createDAO();
}
