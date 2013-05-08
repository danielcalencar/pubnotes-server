package br.ufrn.dimap.pubnote.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Every DAO should extends this class
 * @author daniel
 *
 * @param <T>
 */
public abstract class DAO<T> {
	
	protected final static Session session = buildSession();

	/** methods for persist date must go here **/
	public abstract void persist(T obj);
	
	public abstract T load(long id);
	
	public abstract void update(T obj);
	
	
	/**
	 * building session
	 * @return
	 */
	private static Session buildSession() 
	{

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration
                        .getProperties());
        SessionFactory sessionFactory = configuration
                        .buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
        return sessionFactory.openSession();
    }
	
}
