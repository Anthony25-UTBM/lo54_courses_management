package com.lo54.courses_management.core.repository;

import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class DefaultDAO<T extends Item> {

    protected Logger LOGGER;
    protected Class<T> entityType;

    public DefaultDAO() {
        LOGGER = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * To insert an entity into the database
     * @param entity
     */
    public void insertEntity(T entity) {
        Session session = HibernateUtil.getSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.persist(entity);
            tr.commit();
        } catch(Exception e) {
            LOGGER.error("error in insertEntity: " + e);
            if(tr != null)
                tr.rollback();
        }
    }

    /**
     * To update an entity into the database
     * @param id
     * @param entity
     */
    public void updateEntity(int id, T entity) {

    }

    /**
     * To remove an entity into the database
     * @param id
     */
    public void removeEntity(int id) {
        Item entity = getEntity(id);
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
     * @return
     */
    @SuppressWarnings(value="unchecked")
    public T getEntity(int id) {
        Session session = HibernateUtil.getSession();
        T entity = null;
        try {
            session.beginTransaction();
            entity = (T) session.get(entityType.getCanonicalName(), id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("error in getEntity: " + e);
        }
        return entity;
    }

    /**
     * To get all the entities
     * @return
     */
    public List getEntities() {
        Session session = HibernateUtil.getSession();
        List listEntities = null;
        try {
            Query query = session.createQuery("from " + entityType.getCanonicalName());
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("error in getEntities: " + e);
        }
        return listEntities;
    }
}
