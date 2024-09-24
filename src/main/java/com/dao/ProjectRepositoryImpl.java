package com.dao;



import com.dao.ProjectRepository;
import com.db.DatabaseConnection;
import com.models.Project;
import com.models.ProjectStatus;

import javax.print.attribute.standard.MediaSize;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final Connection connection;

    public ProjectRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public int createProject(Project project) throws SQLException {
        String query = "INSERT INTO projects (projectName, surfaceArea, profitMargin, totalCost, projectStatus, clientID) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDouble(2, project.getSurfaceArea());
            stmt.setDouble(3, project.getProfitMargin());
            stmt.setDouble(4, project.getTotalCost());

            // Use projectStatus's name() directly as ENUM
            stmt.setObject(5, project.getProjectStatus().name(), Types.OTHER); // Pass as ENUM type

            stmt.setInt(6, project.getClientID());

            // Execute the query and retrieve the generated ID
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);  // Return the generated project ID
            } else {
                throw new SQLException("Failed to create project, no ID obtained.");
            }
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
                        ProjectStatus.valueOf(rs.getString("projectStatus")), // Convert string to enum
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
                        ProjectStatus.valueOf(rs.getString("projectStatus")), // Convert string to enum
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

            // Set the project status using the enum's name()
            stmt.setString(5, project.getProjectStatus().name());

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

    @Override
    public void updateTotalCost(int projectId, double totalCost) throws SQLException {
        String query = "UPDATE projects SET totalCost = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, totalCost);
            stmt.setInt(2, projectId);
            stmt.executeUpdate();
        }

    }
    public void updateProjectStatus(int projectId, ProjectStatus status) throws SQLException {
        String query = "UPDATE projects SET projectStatus = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, status.name(), Types.OTHER);
            stmt.setInt(2, projectId);
            stmt.executeUpdate();
        }
    }
}

