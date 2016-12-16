package com.lo54.courses_management.core.service;

import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.repository.ClientDAO;
import com.lo54.courses_management.helpers.HibernateTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by anthony on 16/12/16.
 */
public class ClientServiceTest extends HibernateTestHelper {
    private ClientService clientService;
    private Client client;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        clientService = new ClientService();

        client = new Client();
        client.setLastname("lastnameTest");
        client.setFirstname("firstnameTest");
        client.setPhone("phoneTest");
        client.setEmail("emailTest");

        ClientDAO clientDAO = new ClientDAO();
        clientDAO.insertEntity(client);
    }

    @Test
    public void storeEntity() throws Exception {
        Client testingClient = storeMultipleTestingClients(1).get(0);

        assertTrue(clientService.getEntities().contains(testingClient));
    }

    private ArrayList<Client> storeMultipleTestingClients(int nbItems) throws Exception {
        ArrayList<Client> clients = new ArrayList<>();

        int startingId = 1;
        for(int i = startingId; i < nbItems + startingId; i++) {
            Client testingClient = new Client();
            testingClient.setLastname("lastnameTest" + i);
            testingClient.setFirstname("firstnameTest" + i);
            testingClient.setPhone("phoneTest" + i);
            testingClient.setEmail("emailTest" + i);

            clientService.storeEntity(testingClient);
            clients.add(testingClient);
        }

        return clients;
    }

    @Test
    public void updateEntity() throws Exception {
        client.setFirstname("newFirstName");
        clientService.updateEntity(client.getId(), client);

        Client storedClient = (Client) clientService.getEntity(client.getId());
        assertEquals(client.getFirstname(), storedClient.getFirstname());
    }

    @Test
    public void removeEntity() throws Exception {
        clientService.removeEntity(client.getId());
        assertEquals(0, clientService.getEntities().size());
    }

    @Test
    public void getEntity() throws Exception {
        Client storedClient = (Client) clientService.getEntity(client.getId());
        assertEquals(client, storedClient);
    }

    @Test
    public void getEntities() throws Exception {
        ArrayList<Client> addedClients = storeMultipleTestingClients(10);
        ArrayList<Client> storedClients = (ArrayList<Client>) clientService.getEntities();

        // Should contain `client` + everything in addedClients
        assertEquals(1 + addedClients.size(), storedClients.size());
        assertTrue(storedClients.contains(client));
        assertTrue(storedClients.containsAll(addedClients));
    }
}