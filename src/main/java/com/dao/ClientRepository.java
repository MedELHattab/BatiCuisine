package com.dao;

import com.models.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientRepository {
    int create(Client client) throws SQLException;
    Client read(int clientId) throws SQLException;
    List<Client> readAll() throws SQLException;
    void update(Client client) throws SQLException;
    void delete(int clientId) throws SQLException;
}

