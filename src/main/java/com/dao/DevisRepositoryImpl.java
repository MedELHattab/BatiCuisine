package com.dao;

import com.db.DatabaseConnection;
import com.models.Devis;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevisRepositoryImpl implements DevisRepository {
    private final Connection connection;

    public DevisRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }


    public void createDevis(Devis devis) throws SQLException {
        String query = "INSERT INTO devis (estimatedAmount, issueDate, validityDate, accepted, projectID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, devis.getEstimatedAmount());

            // Convert java.util.Date to java.sql.Date
            stmt.setDate(2, new java.sql.Date(devis.getIssueDate().getTime())); // Assuming issueDate is java.util.Date
            stmt.setDate(3, new java.sql.Date(devis.getValidityDate().getTime())); // Assuming validityDate is java.util.Date

            stmt.setBoolean(4, devis.isAccepted());
            stmt.setInt(5, devis.getProjectID());
            stmt.executeUpdate();
        }
    }
}
