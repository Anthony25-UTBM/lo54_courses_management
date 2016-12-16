package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.Location;
import com.lo54.courses_management.core.repository.LocationDAO;
import com.lo54.courses_management.helpers.HibernateTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by anthony on 16/12/16.
 */
public class LocationServiceTest extends HibernateTestHelper {
    private LocationService locationService;
    private Location location;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        locationService = new LocationService();

        location = new Location();
        location.setCity("LocationTest");

        LocationDAO locationDAO = new LocationDAO();
        locationDAO.insertEntity(location);
    }

    @Test
    public void storeEntity() throws Exception {
        Location testingLocation = storeMultipleTestingLocations(1).get(0);

        assertTrue(locationService.getEntities().contains(testingLocation));
    }

    private ArrayList<Location> storeMultipleTestingLocations(int nbItems) throws Exception {
        ArrayList<Location> locations = new ArrayList<>();

        int startingId = 1;
        for(int i = startingId; i < nbItems + startingId; i++) {
            Location testingLocation = new Location();
            testingLocation.setCity("LocationTest" + i);

            locationService.storeEntity(testingLocation);
            locations.add(testingLocation);
        }

        return locations;
    }

    @Test
    public void updateEntity() throws Exception {
        location.setCity("NewLocationTitle");
        locationService.updateEntity(location.getId(), location);

        Location storedLocation = (Location) locationService.getEntity(location.getId());
        assertEquals(location.getCity(), storedLocation.getCity());
    }

    @Test
    public void removeEntity() throws Exception {
        locationService.removeEntity(location.getId());
        assertEquals(0, locationService.getEntities().size());
    }

    @Test
    public void getEntity() throws Exception {
        Location storedLocation = (Location) locationService.getEntity(location.getId());
        assertEquals(location, storedLocation);
    }

    @Test
    public void getEntities() throws Exception {
        ArrayList<Location> addedLocations = storeMultipleTestingLocations(10);
        ArrayList<Location> storedLocations = (ArrayList<Location>) locationService.getEntities();

        // Should contain `location` + everything in addedLocations
        assertEquals(1 + addedLocations.size(), storedLocations.size());
        assertTrue(storedLocations.contains(location));
        assertTrue(storedLocations.containsAll(addedLocations));
    }
}