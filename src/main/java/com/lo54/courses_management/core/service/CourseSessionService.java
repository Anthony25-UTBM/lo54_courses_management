package com.lo54.courses_management.core.service;


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
        this.courseSessionDao.insertEntity(entity);
    }

    public void updateEntity(int id, Item entity) {
        this.courseSessionDao.updateEntity(id, entity);
    }

    public void removeEntity(int id) {
        this.courseSessionDao.removeEntity(id);
    }

    public Item getEntity(int id) {
        return this.courseSessionDao.getEntity(id);
    }

    public List<Item> getEntities() {
        return this.courseSessionDao.getEntities();
    }

    public List<Item> getEntitiesByTitle(final String filter) {
        return this.courseSessionDao.getEntitiesByTitle(filter);
    }

    public List<Item> getEntitiesByLocation(final String filter) {
        return this.courseSessionDao.getEntitiesByLocation(filter);
    }

    public List<Item> getEntitiesByTimeStamp(final Timestamp tmin, final Timestamp tmax) {
        return this.courseSessionDao.getEntitiesByTimeStamp(tmin, tmax);
    }
}
