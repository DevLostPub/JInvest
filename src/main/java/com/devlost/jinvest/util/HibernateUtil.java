package com.devlost.jinvest.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author lucas
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        //try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        /*} catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }*/
    }

    public static SessionFactory getSessionFactory() throws ExceptionInInitializerError{
        return sessionFactory;
    }
}
