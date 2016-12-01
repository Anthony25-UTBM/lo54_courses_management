package com.lo54.courses_management.core.repository;


import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.entity.Location;

import java.util.List;

public class LocationDAO implements DAO{

    @Override
    public void insertEntity(final Item entity) {
        DefaultDAO.insertEntity(entity);
    }

    @Override
    public void updateEntity(final int id, final Item entity) {
        DefaultDAO.updateEntity( id, Location.class.getCanonicalName(), entity);
    }

    @Override
    public void removeEntity(final int id) {
        DefaultDAO.removeEntity( id, LocationDAO.class.getCanonicalName());
    }

    @Override
    public Item getEntity(final int id) {
        return DefaultDAO.getEntity( id, Location.class.getCanonicalName());
    }

    @Override
    public List<Item> getEntities() {
        return DefaultDAO.getEntities(Location.class.getCanonicalName());
    }
}
