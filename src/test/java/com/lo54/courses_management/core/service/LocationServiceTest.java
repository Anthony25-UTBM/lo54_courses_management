package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.Item;
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
public class LocationServiceTest extends BaseServiceTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new LocationService();

        Location location = new Location();
        location.setCity("LocationTest");

        LocationDAO locationDAO = new LocationDAO();
        locationDAO.insertEntity(location);

        item = location;
    }

    protected ArrayList<Item> storeMultipleTestingItems(int nbItems) throws Exception {
        ArrayList<Item> locations = new ArrayList<>();

        int startingId = 1;
        for(int i = startingId; i < nbItems + startingId; i++) {
            Location testingLocation = new Location();
            testingLocation.setCity("LocationTest" + i);

            service.storeEntity(testingLocation);
            locations.add(testingLocation);
        }

        return locations;
    }

    @Test
    public void updateEntity() throws Exception {
        Location location = (Location) item;

        location.setCity("NewLocationTitle");
        service.updateEntity(location.getId(), location);

        Location storedLocation = (Location) service.getEntity(location.getId());
        assertEquals(location.getCity(), storedLocation.getCity());
    }
}