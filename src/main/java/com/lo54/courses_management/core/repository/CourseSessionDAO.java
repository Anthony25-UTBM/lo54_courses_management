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

public class CourseSessionDAO extends DefaultDAO<CourseSession> {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(CourseSessionDAO.class);

    public CourseSessionDAO() {
        super();
        entityType = CourseSession.class;
    }

    public List getEntitiesByTimeStamp(Timestamp tmin, Timestamp tmax) {
        final Session session = HibernateUtil.getSession();
        List listEntities = null;

        try {
            Query query = session.createQuery(
                "from " + CourseSession.class.getCanonicalName() +
                " c where c.startDate < :timeStampMax and c.startDate > :timeStampMin"
            );
            query.setParameter("timeStampMin", tmin);
            query.setParameter("timeStampMax", tmax);
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("getEntities" + e);
        }
        return listEntities;
    }

    public List<CourseSession> getEntitiesByLocation(String filter) {
        final Session session = HibernateUtil.getSession();
        List listEntities = null;

        try {
            Query query = session.createQuery(
                "from " + Location.class.getCanonicalName() + " where city = :cityFilter"
            );
            query.setParameter("cityFilter", filter);
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("getEntities" + e);
            listEntities = new ArrayList();
        }

        List<CourseSession> listResult = new ArrayList<>();

        for(Object entity : listEntities) {
            Location location = (Location) entity;

            try {
                Query query = session.createQuery(
                    "from " + CourseSession.class.getCanonicalName() + " where location = :locationFilter"
                );
                query.setParameter("locationFilter", location);
                listResult.addAll(query.list());
            }catch (HibernateException e) {
                LOGGER.error("getEntities" + e);
            }
        }
        return listResult;
    }

    public List<CourseSession> getEntitiesByTitle(String filter) {
        final Session session = HibernateUtil.getSession();
        List<Course> listEntities = null;
        try {
            Query query = session.createQuery(
                "from " + Course.class.getCanonicalName() + " where title like :titleFilter"
            );
            query.setParameter("titleFilter", "%" + filter + "%");
            listEntities = query.list();
        }catch (HibernateException e) {
            LOGGER.error("getEntities" + e);
        }

        List<CourseSession> listEntities2 = null;

        if (listEntities != null) {
            for(Course course : listEntities) {
                try {
                    Query query = session.createQuery(
                        "from " + CourseSession.class.getCanonicalName() + " where course like :code"
                    );
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
