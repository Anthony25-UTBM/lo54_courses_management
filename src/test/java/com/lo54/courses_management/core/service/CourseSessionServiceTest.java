package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.*;
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
public class CourseSessionServiceTest extends BaseServiceTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new CourseSessionService();

        CourseSession courseSession = new CourseSession();

        courseSession.setClients(genClients(1));
        courseSession.setLocation(genLocation(0));
        courseSession.setCourse(genCourse(1));
        courseSession.setStartDate(new Date());
        courseSession.setEndDate(new Date());

        CourseSessionDAO courseSessionDAO = new CourseSessionDAO();
        courseSessionDAO.insertEntity(courseSession);

        item = courseSession;
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

    protected ArrayList<Item> storeMultipleTestingItems(int nbItems) throws Exception {
        ArrayList<Item> courseSessions = new ArrayList<>();

        int startingId = 2;
        for(int i = startingId; i < nbItems + startingId; i++) {
            CourseSession testingCourseSession = new CourseSession();

            testingCourseSession.setClients(genClients(2));
            testingCourseSession.setLocation(genLocation(i));
            testingCourseSession.setCourse(genCourse(i));
            testingCourseSession.setStartDate(new Date());
            testingCourseSession.setEndDate(new Date());

            service.storeEntity(testingCourseSession);
            courseSessions.add(testingCourseSession);
        }

        return courseSessions;
    }

    @Test
    public void updateEntity() throws Exception {
        CourseSession courseSession = (CourseSession) item;

        courseSession.setLocation(genLocation(2));
        service.updateEntity(courseSession.getId(), courseSession);

        CourseSession storedCourseSession = (CourseSession) service.getEntity(courseSession.getId());
        assertEquals(courseSession.getLocation(), storedCourseSession.getLocation());
    }
}