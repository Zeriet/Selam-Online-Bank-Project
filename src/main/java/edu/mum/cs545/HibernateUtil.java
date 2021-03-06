package edu.mum.cs545;

import org.hibernate.tool.hbm2ddl.SchemaExport;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import edu.mum.cs545.model.*;

public class HibernateUtil 
{
	private static SessionFactory factory;
	
	public static Configuration getInitializedConfiguration()
	{
		//AnnotationConfiguration config = new AnnotationConfiguration();
                Configuration config = new Configuration();
		//Bring all annotated class here you want to use.
		config.addAnnotatedClass(Customer.class);
		config.addAnnotatedClass(Account.class);
		config.addAnnotatedClass(Transfer.class);
		
		config.configure();
		
		return config;
	}
	
	public static Session getSession()
	{
		if(factory == null)
		{
			Configuration config = HibernateUtil.getInitializedConfiguration();
			factory = config.buildSessionFactory();
		}
		Session hibernateSession = factory.getCurrentSession();
		
		return hibernateSession;
	}
	
	public static void recreateDatabase()
	{
		Configuration config = HibernateUtil.getInitializedConfiguration(); //WE dont need session in creating schema, we need session in persisting
		new SchemaExport(config).create(true, true);
	}
	
	public static Session beginTransaction()
	{
		Session hibernateSession = HibernateUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	
	public static void commitTransaction(){HibernateUtil.getSession().getTransaction().commit();}
	public static void rollbackTransaction(){HibernateUtil.getSession().getTransaction().rollback();}
	public static void closeSession(){HibernateUtil.getSession().close();}
	
	public static void main (String[] Args)
	{
		HibernateUtil.recreateDatabase();
	}
}