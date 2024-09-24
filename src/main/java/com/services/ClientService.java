package com.services;

import com.dao.ClientRepositoryImpl;
import com.models.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientRepositoryImpl clientRepository;

    public ClientService() throws SQLException {
        this.clientRepository = new ClientRepositoryImpl();
    }

    public int createClient(Client client) throws SQLException {
        return clientRepository.create(client); // Ensure that this is returning the result from the repository layer
    }

    public Client getClientById(int clientId) throws SQLException {
        return clientRepository.getClientById(clientId);
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
