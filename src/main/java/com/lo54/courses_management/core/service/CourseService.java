package com.lo54.courses_management.core.service;


import com.lo54.courses_management.core.entity.Course;
import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.repository.CourseDAO;

import java.util.List;

public class CourseService implements Service{

    private CourseDAO courseDao;

    public CourseService(){
        this.courseDao = new CourseDAO();
    }

    public void storeEntity(Item entity) {
        this.courseDao.insertEntity((Course) entity);
    }

    public void updateEntity(int id, Item entity) {
        this.courseDao.updateEntity(id, (Course) entity);
    }

    public void removeEntity(int id) {
        this.courseDao.removeEntity(id);
    }

    public Item getEntity(int id) {
        return this.courseDao.getEntity(id);
    }

    public List getEntities() {
        return this.courseDao.getEntities();
    }
}
