package com.lo54.courses_management.core.service;


import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.entity.Location;
import com.lo54.courses_management.core.repository.LocationDAO;

import java.util.List;

public class LocationService implements Service{

    private final LocationDAO locationDao;

    public LocationService() {
        locationDao = new LocationDAO();
    }

    public void storeEntity(Item entity) {
        this.locationDao.insertEntity((Location) entity);

    }

    public void updateEntity(int id, Item entity) throws Exception {
        this.locationDao.updateEntity(id, (Location) entity);

    }

    public void removeEntity(int id) throws Exception {
        this.locationDao.removeEntity(id);

    }

    public Item getEntity(int id) throws Exception {
        return this.locationDao.getEntity(id);
    }

    public List getEntities() throws Exception {
        return this.locationDao.getEntities();
    }
}
