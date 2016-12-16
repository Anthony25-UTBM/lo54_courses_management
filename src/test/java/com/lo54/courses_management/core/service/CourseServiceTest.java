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
public class CourseServiceTest extends HibernateTestHelper {
    private CourseService courseService;
    private Course course;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        courseService = new CourseService();

        course = new Course();
        course.setTitle("CourseTest");

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.insertEntity(course);
    }

    @Test
    public void storeEntity() throws Exception {
        Course testingCourse = storeMultipleTestingCourses(1).get(0);

        assertTrue(courseService.getEntities().contains(testingCourse));
    }

    private ArrayList<Course> storeMultipleTestingCourses(int nbItems) throws Exception {
        ArrayList<Course> courses = new ArrayList<>();

        int startingId = 1;
        for(int i = startingId; i < nbItems + startingId; i++) {
            Course testingCourse = new Course();
            testingCourse.setId(i);
            testingCourse.setTitle("CourseTest" + i);

            courseService.storeEntity(testingCourse);
            courses.add(testingCourse);
        }

        return courses;
    }

    @Test
    public void updateEntity() throws Exception {
        course.setTitle("NewCourseTitle");
        courseService.updateEntity(course.getId(), course);

        Course storedCourse = (Course) courseService.getEntity(course.getId());
        assertEquals(course.getTitle(), storedCourse.getTitle());
    }

    @Test
    public void removeEntity() throws Exception {
        courseService.removeEntity(course.getId());
        assertEquals(0, courseService.getEntities().size());
    }

    @Test
    public void getEntity() throws Exception {
        Course storedCourse = (Course) courseService.getEntity(course.getId());
        assertEquals(course, storedCourse);
    }

    @Test
    public void getEntities() throws Exception {
        ArrayList<Course> addedCourses = storeMultipleTestingCourses(10);
        ArrayList<Course> storedCourses = (ArrayList<Course>) courseService.getEntities();

        // Should contain `course` + everything in addedCourses
        assertEquals(1 + addedCourses.size(), storedCourses.size());
        assertTrue(storedCourses.contains(course));
        assertTrue(storedCourses.containsAll(addedCourses));
    }
}