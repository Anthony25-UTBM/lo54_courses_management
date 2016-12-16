package com.lo54.courses_management.helpers;

import com.fasterxml.classmate.AnnotationConfiguration;
import com.lo54.courses_management.core.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import java.lang.annotation.Annotation;
import java.sql.SQLException;

/**
 * Created by anthony on 16/12/16.
 */
public abstract class HibernateTestHelper {
    private static Server h2server;

    @Before
    public void setUp() throws Exception {
        Configuration configuration = createTestingConfiguration();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        HibernateUtil.setSessionFactory(sessionFactory);
    }

    /**
     * Configure hibernate to use H2, an in-memory database
     *
     * @return
     */
    protected Configuration createTestingConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:test");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        return configuration;
    }

    @After
    public void tearDown() throws Exception {
        if(HibernateUtil.getSession().isOpen())
            HibernateUtil.getSession().close();
    }
}
