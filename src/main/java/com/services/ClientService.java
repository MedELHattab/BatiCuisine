package com.services;

import com.dao.ClientRepository;
import com.dao.ClientRepositoryImpl;
import com.models.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService() throws SQLException {
        this.clientRepository = new ClientRepositoryImpl();
    }

    public void createClient(Client client) throws SQLException {
        clientRepository.create(client);
    }

    public Client getClient(int clientId) throws SQLException {
        return clientRepository.read(clientId);
    }

    public List<Client> getAllClients() throws SQLException {
        return clientRepository.readAll();
    }

    public void updateClient(Client client) throws SQLException {
        clientRepository.update(client);
    }

    public void deleteClient(int clientId) throws SQLException {
        clientRepository.delete(clientId);
    }
}
