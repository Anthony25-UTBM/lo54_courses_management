package com.lo54.courses_management.core.repository;


import com.lo54.courses_management.core.entity.Course;
import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.entity.Location;
import com.lo54.courses_management.core.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseSessionDAO implements DAO {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(CourseSessionDAO.class);

    @Override
    public void insertEntity(final Item entity) {
        DefaultDAO.insertEntity(entity);
    }

    @Override
    public void updateEntity(final int id, final Item entity) {
        DefaultDAO.updateEntity( id, CourseSession.class.getCanonicalName(), entity);
    }

    @Override
    public void removeEntity(final int id) {
        DefaultDAO.removeEntity(id, CourseSession.class.getCanonicalName());
    }

    @Override
    public Item getEntity(final int id) {
        return DefaultDAO.getEntity( id, CourseSession.class.getCanonicalName());
    }

    @Override
    public List<Item> getEntities() {
        return DefaultDAO.getEntities(CourseSession.class.getCanonicalName());
    }

    public List<Item> getEntitiesByTimeStamp(final Timestamp tmin, final Timestamp tmax) {
        final Session session = HibernateUtil.getSession();
        List<Item> listEntities = null;

        try {
            Query query = session.createQuery("from " + CourseSession.class.getCanonicalName() + " c where c.startDate < :timeStampMax and c.startDate > :timeStampMin");
            query.setParameter("timeStampMin", tmin);
            query.setParameter("timeStampMax", tmax);
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("getEntities" + e);
        }
        return listEntities;
    }

    public List<Item> getEntitiesByLocation(final String filter) {
        final Session session = HibernateUtil.getSession();
        List<Item> listEntities = null;

        try {
            Query query = session.createQuery("from " + Location.class.getCanonicalName() + " where city = :cityFilter");
            query.setParameter("cityFilter", filter);
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("getEntities" + e);
        }

        List<Item> listResult;
        listResult = new ArrayList<>();

        for(Item entity : listEntities) {
            Location location = (Location) entity;

            List<Item> listEntities2 = null;

            try {
                Query query = session.createQuery("from " + CourseSession.class.getCanonicalName() + " where location = :locationFilter");
                query.setParameter("locationFilter", location);
                listEntities2 = query.list();
                listResult.addAll(listEntities2);
            }catch (HibernateException e) {
                LOGGER.error("getEntities" + e);
            }
        }
        return listResult;
    }

    public List<Item> getEntitiesByTitle(final String filter) {
        final Session session = HibernateUtil.getSession();
        List<Item> listEntities = null;
        try {
            Query query = session.createQuery("from " + Course.class.getCanonicalName() + " where title like :titleFilter");
            query.setParameter("titleFilter", "%" + filter + "%");
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("getEntities" + e);
        }

        List<Item> listEntities2 = null;

        if (listEntities != null) {
            for(Item entity : listEntities) {
                Course course = (Course) entity;
                try {
                    Query query = session.createQuery("from " + CourseSession.class.getCanonicalName() + " where course like :code");
                    query.setParameter("code", course);
                    listEntities2 = query.list();
                }catch (HibernateException e) {
                    LOGGER.error("getEntities" + e);
                }
            }
        }
        return listEntities2;
    }
}
