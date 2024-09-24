package com.dao;

import com.db.DatabaseConnection;
import com.models.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    private Connection connection;

    public ClientRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public int create(Client client) throws SQLException {
        String sql = "INSERT INTO clients (name, address, phoneNumber, isProfessional) VALUES (?, ?, ?, ?) RETURNING id"; // Add RETURNING clause
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setBoolean(4, client.isProfessional());
            ResultSet rs = stmt.executeQuery(); // Use executeQuery to get the generated ID
            if (rs.next()) {
                return rs.getInt("id"); // Return the generated ID
            }
            throw new SQLException("Failed to retrieve client ID."); // Throw an exception if ID is not retrieved
        }
    }


    @Override
    public Client getClientById(int clientId) throws SQLException {
        String query = "SELECT * FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("address"),
                            resultSet.getString("phoneNumber"),
                            resultSet.getBoolean("isProfessional")
                    );
                }
            }
        }
        return null; // Return null if no client is found
    }

    @Override
    public List<Client> readAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(mapRowToClient(rs));
            }
        }
        return clients;
    }

    @Override
    public void update(Client client) throws SQLException {
        String sql = "UPDATE clients SET name = ?, address = ?, phoneNumber = ?, isProfessional = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setBoolean(4, client.isProfessional());
            stmt.setInt(5, client.getClientId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Client mapRowToClient(ResultSet rs) throws SQLException {
        return new Client(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phoneNumber"),
                rs.getBoolean("isProfessional")
        );
    }
}

