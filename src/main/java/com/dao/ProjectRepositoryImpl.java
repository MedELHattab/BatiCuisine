package com.dao;



import com.dao.ProjectRepository;
import com.db.DatabaseConnection;
import com.models.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final Connection connection;

    public ProjectRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void createProject(Project project) throws SQLException {
        String query = "INSERT INTO projects (projectName, surfaceArea, profitMargin, totalCost, projectStatus, clientID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDouble(2, project.getSurfaceArea());
            stmt.setDouble(3, project.getProfitMargin());
            stmt.setDouble(4, project.getTotalCost());
            stmt.setString(5, project.getProjectStatus());
            stmt.setInt(6, project.getClientID());
            stmt.executeUpdate();
        }
    }

    @Override
    public Project getProjectById(int id) throws SQLException {
        String query = "SELECT * FROM projects WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(
                        rs.getInt("id"),
                        rs.getString("projectName"),
                        rs.getDouble("surfaceArea"),
                        rs.getDouble("profitMargin"),
                        rs.getDouble("totalCost"),
                        rs.getString("projectStatus"),
                        rs.getInt("clientID")
                );
            }
            return null;
        }
    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("id"),
                        rs.getString("projectName"),
                        rs.getDouble("surfaceArea"),
                        rs.getDouble("profitMargin"),
                        rs.getDouble("totalCost"),
                        rs.getString("projectStatus"),
                        rs.getInt("clientID")
                ));
            }
        }
        return projects;
    }

    @Override
    public void updateProject(Project project) throws SQLException {
        String query = "UPDATE projects SET projectName = ?, surfaceArea = ?, profitMargin = ?, totalCost = ?, projectStatus = ?, clientID = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDouble(2, project.getSurfaceArea());
            stmt.setDouble(3, project.getProfitMargin());
            stmt.setDouble(4, project.getTotalCost());
            stmt.setString(5, project.getProjectStatus());
            stmt.setInt(6, project.getClientID());
            stmt.setInt(7, project.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteProject(int id) throws SQLException {
        String query = "DELETE FROM projects WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

