package com.lo54.courses_management.core.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get an open session.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session = null;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println( "Initial SessionFactory creation failed." + ex );
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void setSessionFactory(SessionFactory _sessionFactory) {
        sessionFactory = _sessionFactory;
    }

    public static Session getSession(){
        if(session == null || !session.isOpen()) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    public static void setSession(Session _session) {
        session = _session;
    }
}