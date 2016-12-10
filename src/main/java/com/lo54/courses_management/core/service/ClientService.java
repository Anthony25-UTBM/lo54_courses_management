package com.lo54.courses_management.core.service;


import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.repository.ClientDAO;

import java.util.List;

public class ClientService implements Service{

    private ClientDAO clientDao;

    public ClientService(){
        clientDao = new ClientDAO();
    }

    public void storeEntity(Item entity) {
        this.clientDao.insertEntity((Client) entity);
    }

    public void updateEntity(int id, Item entity) {
        this.clientDao.updateEntity(id, (Client) entity);
    }

    public void removeEntity(int id){
        this.clientDao.removeEntity(id);
    }

    public Item getEntity(int id) {
        return this.clientDao.getEntity(id);
    }

    public List getEntities() {
        return this.clientDao.getEntities();
    }
}
