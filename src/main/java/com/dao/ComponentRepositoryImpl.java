package com.dao;

import com.db.DatabaseConnection;
import com.models.Component;
import com.models.Labor;
import com.models.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponentRepositoryImpl implements ComponentRepository {
    private final Connection connection;

    public ComponentRepositoryImpl() throws SQLException {
        // Get the database connection from the DatabaseConnection Singleton
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Component> getComponentsByProjectId(int projectId) throws SQLException {
        List<Component> components = new ArrayList<>();

        // Fetch materials
        String materialQuery = "SELECT * FROM materials WHERE projectid = ?";
        try (PreparedStatement materialStmt = connection.prepareStatement(materialQuery)) {
            materialStmt.setInt(1, projectId);
            try (ResultSet materialRs = materialStmt.executeQuery()) {
                while (materialRs.next()) {
                    Material material = new Material(
                            materialRs.getInt("id"),
                            materialRs.getString("name"),
                            materialRs.getDouble("quantity"),
                            materialRs.getDouble("unitCost"),
                            materialRs.getDouble("transportCost"),
                            materialRs.getDouble("qualityCoefficient"),
                            materialRs.getDouble("tvaRate")
                    );
                    components.add(material);
                }
            }
        }

        // Fetch labor
        String laborQuery = "SELECT * FROM labors WHERE projectid = ?";
        try (PreparedStatement laborStmt = connection.prepareStatement(laborQuery)) {
            laborStmt.setInt(1, projectId);
            try (ResultSet laborRs = laborStmt.executeQuery()) {
                while (laborRs.next()) {
                    Labor labor = new Labor(
                            laborRs.getInt("id"),
                            laborRs.getString("name"),
                            laborRs.getString("laborType"),
                            laborRs.getDouble("hourlyRate"),
                            laborRs.getDouble("workHours"),
                            laborRs.getDouble("workerProductivity"),
                            laborRs.getDouble("tvaRate")
                    );
                    components.add(labor);
                }
            }
        }

        return components;
    }

    @Override
    public void addComponent(Component component, int projectId) throws SQLException {
        if (component instanceof Material) {
            Material material = (Material) component;
            String materialQuery = "INSERT INTO materials (projectid, name, componentType, quantity, unitCost, transportCost, qualityCoefficient, tvaRate) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement materialStmt = connection.prepareStatement(materialQuery)) {
                materialStmt.setInt(1, projectId);
                materialStmt.setString(2, material.getName());
                materialStmt.setString(3, "Material"); // Set componentType to "Material"
                materialStmt.setDouble(4, material.getQuantity());
                materialStmt.setDouble(5, material.getUnitCost());
                materialStmt.setDouble(6, material.getTransportCost());
                materialStmt.setDouble(7, material.getQualityCoefficient());
                materialStmt.setDouble(8,material.getTvaRate());
                materialStmt.executeUpdate();
            }
        } else if (component instanceof Labor) {
            Labor labor = (Labor) component;
            String laborQuery = "INSERT INTO labors (projectid, name, componentType, laborType, hourlyRate, workHours, workerProductivity, tvaRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement laborStmt = connection.prepareStatement(laborQuery)) {
                laborStmt.setInt(1, projectId);
                laborStmt.setString(2, labor.getName());
                laborStmt.setString(3, "Labor"); // Set componentType to "Labor"
                laborStmt.setString(4, labor.getLaborType());
                laborStmt.setDouble(5, labor.getHourlyRate());
                laborStmt.setDouble(6, labor.getWorkHours());
                laborStmt.setDouble(7, labor.getWorkerProductivity());
                laborStmt.setDouble(8, labor.getTvaRate());
                laborStmt.executeUpdate();
            }
        }
    }

}
