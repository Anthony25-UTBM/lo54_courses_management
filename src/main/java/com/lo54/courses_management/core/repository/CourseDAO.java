package com.lo54.courses_management.core.repository;


import com.lo54.courses_management.core.entity.Course;

public class CourseDAO extends DefaultDAO<Course> {
    public CourseDAO() {
        super();
        entityType = Course.class;
    }
}
