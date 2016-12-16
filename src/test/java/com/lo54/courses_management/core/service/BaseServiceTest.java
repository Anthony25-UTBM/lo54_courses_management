package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.helpers.HibernateTestHelper;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by anthony on 16/12/16.
 */
public abstract class BaseServiceTest extends HibernateTestHelper {
    protected Service service;
    protected Item item;

    @Test
    public void storeEntity() throws Exception {
        Item testingItem = storeMultipleTestingItems(1).get(0);

        assertTrue(service.getEntities().contains(testingItem));
    }

    protected abstract ArrayList<Item> storeMultipleTestingItems(int nbItems) throws Exception;

    @Test
    public void removeEntity() throws Exception {
        service.removeEntity(item.getId());
        assertEquals(0, service.getEntities().size());
    }

    @Test
    public void getEntity() throws Exception {
        Item storedItem = service.getEntity(item.getId());
        assertEquals(item, storedItem);
    }

    @Test
    public void getEntities() throws Exception {
        ArrayList<Item> addedItems = storeMultipleTestingItems(10);
        ArrayList<Item> storedItems = (ArrayList<Item>) service.getEntities();

        // Should contain `courseSession` + everything in addedCourseSessions
        assertEquals(1 + addedItems.size(), storedItems.size());
        assertTrue(storedItems.contains(item));
        assertTrue(storedItems.containsAll(addedItems));
    }
}
