package com.lo54.courses_management.core.repository;


import com.lo54.courses_management.core.entity.Course;
import com.lo54.courses_management.core.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseDAO implements DAO {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(CourseDAO.class);

    @Override
    public void insertEntity(final Item entity) {
        DefaultDAO.insertEntity(entity);
    }

    @Override
    public void updateEntity(final int id, final Item entity) {
        DefaultDAO.updateEntity(id, Course.class.getCanonicalName(), entity);
    }

    @Override
    public List<Item> getEntities() {
        return DefaultDAO.getEntities(Course.class.getCanonicalName());
    }

    @Override
    public void removeEntity(final int id) {
        DefaultDAO.removeEntity(id, Course.class.getCanonicalName());
    }

    @Override
    public Item getEntity(final int id) {
        return DefaultDAO.getEntity(id, Course.class.getCanonicalName());
    }
}
