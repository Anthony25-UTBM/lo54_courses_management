package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.Course;
import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.repository.CourseDAO;
import com.lo54.courses_management.helpers.HibernateTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by anthony on 16/12/16.
 */
public class CourseServiceTest extends BaseServiceTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new CourseService();

        Course course = new Course();
        course.setTitle("CourseTest");

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.insertEntity(course);

        item = course;
    }

    protected ArrayList<Item> storeMultipleTestingItems(int nbItems) throws Exception {
        ArrayList<Item> courses = new ArrayList<>();

        int startingId = 1;
        for(int i = startingId; i < nbItems + startingId; i++) {
            Course testingCourse = new Course();
            testingCourse.setId(i);
            testingCourse.setTitle("CourseTest" + i);

            service.storeEntity(testingCourse);
            courses.add(testingCourse);
        }

        return courses;
    }

    @Test
    public void updateEntity() throws Exception {
        Course course = (Course) item;

        course.setTitle("NewCourseTitle");
        service.updateEntity(course.getId(), course);

        Course storedCourse = (Course) service.getEntity(course.getId());
        assertEquals(course.getTitle(), storedCourse.getTitle());
    }
}