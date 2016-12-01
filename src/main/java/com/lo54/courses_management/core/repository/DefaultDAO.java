package com.lo54.courses_management.core.repository;

import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultDAO {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(DefaultDAO.class);

    /**
     * To insert an entity into the database
     * @param entity
     */
    public static void insertEntity(final Item entity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch(HibernateException e) {
            LOGGER.error("error in insertEntity: " + e);
        }
    }

    /**
     * To update an entity into the database
     * @param id
     * @param clazz
     * @param entity
     */
    public static void updateEntity(final int id, final String clazz, final Item entity) {

    }

    /**
     * To update an entity into the database
     * @param id
     * @param clazz
     * @param entity
     */
    public static void updateEntity(final String id, final String clazz, final Item entity) {

    }

    /**
     * To remove an entity into the database
     * @param id
     * @param clazz
     */
    public static void removeEntity(final int id, final String clazz) {
        Item entity = DefaultDAO.getEntity(id, clazz);
        Session session = HibernateUtil.getSession();
        try{
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("error in removeEntity: " + e);
        }
    }

    /**
     * To remove an entity into the database
     * @param id
     * @param clazz
     */
    public static void removeEntity(final String id, final String clazz) {
        Item entity = DefaultDAO.getEntity(id, clazz);
        Session session = HibernateUtil.getSession();
        try{
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("error in removeEntity: " + e);
        }
    }

    /**
     * To get an entity from his id and class
     * @param id
     * @param clazz
     * @return
     */
    public static Item getEntity(final int id, final String clazz) {
        final Session session = HibernateUtil.getSession();
        Item entity = null;
        try {
            session.beginTransaction();
            entity = (Item) session.get(clazz, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("error in getEntity: " + e);
        }
        return entity;
    }

    /**
     * To get an entity from his id and class
     * @param id
     * @param clazz
     * @return
     */
    public static Item getEntity(final String id, final String clazz) {
        final Session session = HibernateUtil.getSession();
        Item entity = null;
        try {
            session.beginTransaction();
            entity = (Item) session.get(clazz, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("error in getEntity: " + e);
        }
        return entity;
    }

    /**
     * To get all the entities
     * @param clazz
     * @return
     */
    public static List<Item> getEntities(final String clazz) {
        final Session session = HibernateUtil.getSession();
        List<Item> listEntities = null;
        try {
            Query query = session.createQuery("from " + clazz);
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("error in getEntities: " + e);
        }
        return listEntities;
    }
}
