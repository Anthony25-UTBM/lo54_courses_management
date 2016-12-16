package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.entity.Course;
import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.entity.Location;
import com.lo54.courses_management.core.repository.ClientDAO;
import com.lo54.courses_management.core.repository.CourseDAO;
import com.lo54.courses_management.core.repository.CourseSessionDAO;
import com.lo54.courses_management.core.repository.LocationDAO;
import com.lo54.courses_management.helpers.HibernateTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by anthony on 16/12/16.
 */
public class CourseSessionServiceTest extends HibernateTestHelper {
    private CourseSessionService courseSessionService;
    private CourseSession courseSession;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        courseSessionService = new CourseSessionService();

        courseSession = new CourseSession();

        courseSession.setClients(genClients(1));
        courseSession.setLocation(genLocation(0));
        courseSession.setCourse(genCourse(1));
        courseSession.setStartDate(new Date());
        courseSession.setEndDate(new Date());

        CourseSessionDAO courseSessionDAO = new CourseSessionDAO();
        courseSessionDAO.insertEntity(courseSession);
    }

    private Set<Client> genClients(int nbClients) {
        ClientDAO clientDAO = new ClientDAO();
        Set<Client> clients = new HashSet<Client>();

        for(int i = 0; i < nbClients; i++) {
            Client client = new Client();

            client.setLastname("lastnameTest" + i);
            client.setFirstname("firstnameTest" + i);
            client.setPhone("phoneTest" + i);
            client.setEmail("emailTest" + i);

            // TODO: catch the appropriate exception here
            try {
                clientDAO.insertEntity(client);
            } catch (Exception ignored) {}
            clients.add(client);
        }

        return clients;
    }

    private Location genLocation(int id) {
        LocationDAO locationDAO = new LocationDAO();

        Location location = new Location();
        location.setCity("LocationTest" + id);

        try {
            locationDAO.insertEntity(location);
        } catch (Exception ignored) {}
        return location;
    }

    private Course genCourse(int id) {
        CourseDAO courseDAO = new CourseDAO();

        Course course = new Course();
        course.setTitle("CourseTest" + id);
        course.setId(id);

        try {
            courseDAO.insertEntity(course);
        } catch (Exception ignored) {}
        return course;
    }


    @Test
    public void storeEntity() throws Exception {
        CourseSession testingCourseSession = storeMultipleTestingCourseSessions(1).get(0);

        assertTrue(courseSessionService.getEntities().contains(testingCourseSession));
    }

    private ArrayList<CourseSession> storeMultipleTestingCourseSessions(int nbItems) throws Exception {
        ArrayList<CourseSession> courseSessions = new ArrayList<>();

        int startingId = 1;
        for(int i = startingId; i < nbItems + startingId; i++) {
            CourseSession testingCourseSession = new CourseSession();

            courseSession.setClients(genClients(2));
            courseSession.setLocation(genLocation(i));
            courseSession.setCourse(genCourse(i));
            courseSession.setStartDate(new Date());
            courseSession.setEndDate(new Date());

            courseSessionService.storeEntity(testingCourseSession);
            courseSessions.add(testingCourseSession);
        }

        return courseSessions;
    }

    @Test
    public void updateEntity() throws Exception {
        courseSession.setLocation(genLocation(2));
        courseSessionService.updateEntity(courseSession.getId(), courseSession);

        CourseSession storedCourseSession = (CourseSession) courseSessionService.getEntity(courseSession.getId());
        assertEquals(courseSession.getLocation(), storedCourseSession.getLocation());
    }

    @Test
    public void removeEntity() throws Exception {
        courseSessionService.removeEntity(courseSession.getId());
        assertEquals(0, courseSessionService.getEntities().size());
    }

    @Test
    public void getEntity() throws Exception {
        CourseSession storedCourseSession = (CourseSession) courseSessionService.getEntity(courseSession.getId());
        assertEquals(courseSession, storedCourseSession);
    }

    @Test
    public void getEntities() throws Exception {
        ArrayList<CourseSession> addedCourseSessions = storeMultipleTestingCourseSessions(10);
        ArrayList<CourseSession> storedCourseSessions = (ArrayList<CourseSession>) courseSessionService.getEntities();

        // Should contain `courseSession` + everything in addedCourseSessions
        assertEquals(1 + addedCourseSessions.size(), storedCourseSessions.size());
        // assertTrue(storedCourseSessions.contains(courseSession));
        assertTrue(storedCourseSessions.containsAll(addedCourseSessions));
    }
}