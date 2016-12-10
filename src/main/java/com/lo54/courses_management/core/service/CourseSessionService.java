package com.lo54.courses_management.core.service;


import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.repository.CourseSessionDAO;

import java.sql.Timestamp;
import java.util.List;

public class CourseSessionService implements Service {

    CourseSessionDAO courseSessionDao;

    public CourseSessionService(){
        courseSessionDao = new CourseSessionDAO();
    }

    public void storeEntity(Item entity) {
        courseSessionDao.insertEntity((CourseSession) entity);
    }

    public void updateEntity(int id, Item entity) {
        courseSessionDao.updateEntity(id, (CourseSession) entity);
    }

    public void removeEntity(int id) {
        courseSessionDao.removeEntity(id);
    }

    public Item getEntity(int id) {
        return courseSessionDao.getEntity(id);
    }

    public List getEntities() {
        return courseSessionDao.getEntities();
    }

    public List getEntitiesByTitle(String filter) {
        return courseSessionDao.getEntitiesByTitle(filter);
    }

    public List getEntitiesByLocation(String filter) {
        return this.courseSessionDao.getEntitiesByLocation(filter);
    }

    public List getEntitiesByTimeStamp(Timestamp tmin, Timestamp tmax) {
        return this.courseSessionDao.getEntitiesByTimeStamp(tmin, tmax);
    }
}
