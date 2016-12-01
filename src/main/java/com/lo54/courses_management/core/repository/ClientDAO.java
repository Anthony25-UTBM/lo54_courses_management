package com.lo54.courses_management.core.repository;


import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.entity.Item;

import java.util.List;

public class ClientDAO implements DAO{

    public void insertEntity(Item entity) {
        DefaultDAO.insertEntity(entity);

    }

    public void updateEntity(int id, Item entity) {
        DefaultDAO.updateEntity(id, Client.class.getCanonicalName(), entity);

    }

    public void removeEntity(int id) {
        DefaultDAO.removeEntity(id, Client.class.getCanonicalName());

    }

    public Item getEntity(int id) {
        return DefaultDAO.getEntity( id, Client.class.getCanonicalName());

    }

    public List<Item> getEntities() {
        return DefaultDAO.getEntities(Client.class.getCanonicalName());
    }
}
