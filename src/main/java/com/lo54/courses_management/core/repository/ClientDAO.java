package com.lo54.courses_management.core.repository;


import com.lo54.courses_management.core.entity.Client;

import java.util.List;

public class ClientDAO extends DefaultDAO<Client> {
    public ClientDAO() {
        super();
        entityType = Client.class;
    }
}
