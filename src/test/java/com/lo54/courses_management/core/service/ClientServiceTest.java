package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.entity.Item;
import com.lo54.courses_management.core.repository.ClientDAO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by anthony on 16/12/16.
 */
public class ClientServiceTest extends BaseServiceTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = new ClientService();

        Client client = new Client();
        client.setLastname("lastnameTest");
        client.setFirstname("firstnameTest");
        client.setPhone("phoneTest");
        client.setEmail("emailTest");

        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insertEntity(client);

        item = client;
    }

    protected ArrayList<Item> storeMultipleTestingItems(int nbItems) throws Exception {
        ArrayList<Item> clients = new ArrayList<>();

        int startingId = 1;
        for(int i = startingId; i < nbItems + startingId; i++) {
            Client testingClient = new Client();
            testingClient.setLastname("lastnameTest" + i);
            testingClient.setFirstname("firstnameTest" + i);
            testingClient.setPhone("phoneTest" + i);
            testingClient.setEmail("emailTest" + i);

            service.storeEntity(testingClient);
            clients.add(testingClient);
        }

        return clients;
    }

    @Test
    public void updateEntity() throws Exception {
        Client client = (Client) item;
        client.setFirstname("newFirstName");
        service.updateEntity(client.getId(), client);

        Client storedClient = (Client) service.getEntity(client.getId());
        assertEquals(client.getFirstname(), storedClient.getFirstname());
    }
}