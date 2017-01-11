package com.lo54.courses_management.core.repository;

import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.util.HibernateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
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
            throw e;
        }
    }

    /**
     * To update an entity into the database
     * @param id
     * @param entity
     */
    public void updateEntity(int id, T entity) throws Exception {
        T storedEntity = getEntity(id);

        try {
            BeanUtils.copyProperties(storedEntity, entity);
        } catch (IllegalAccessException|InvocationTargetException e) {
            LOGGER.error("error in updateEntity: " + e);
            throw e;
        }

        Session session = HibernateUtil.getSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.update(entity);
            tr.commit();
        } catch(Exception e) {
            LOGGER.error("error in updateEntity: " + e);
            if(tr != null)
                tr.rollback();
            throw e;
        }
    }

    /**
     * To remove an entity into the database
     * @param id
     */
    public void removeEntity(int id) throws Exception {
        Item entity = getEntity(id);

        Session session = HibernateUtil.getSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.delete(entity);
            tr.commit();
        } catch(Exception e) {
            LOGGER.error("error in removeEntity: " + e);
            if(tr != null)
                tr.rollback();
            throw e;
        }
    }

    /**
     * To get an entity from its id and class
     * @param id
     * @return
     */
    @SuppressWarnings(value="unchecked")
    public T getEntity(int id) throws Exception {
        T entity = null;

        Session session = HibernateUtil.getSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            entity = (T) session.get(entityType.getCanonicalName(), id);
            tr.commit();
        } catch(Exception e) {
            LOGGER.error("error in getEntity: " + e);
            if(tr != null)
                tr.rollback();
            throw e;
        }

        return entity;
    }

    /**
     * To get all entities
     * @return
     */
    public List getEntities() throws Exception {
        List listEntities = null;

        Session session = HibernateUtil.getSession();
        try {
            Query query = session.createQuery("from " + entityType.getCanonicalName());
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("error in getEntities: " + e);
            throw e;
        }
        return listEntities;
    }
}
