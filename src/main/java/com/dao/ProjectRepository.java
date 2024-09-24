
package com.dao;

import com.models.Project;
import com.models.ProjectStatus;

import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {
    int createProject(Project project) throws SQLException;
    Project getProjectById(int id) throws SQLException;
    List<Project> getAllProjects() throws SQLException;
    void updateProject(Project project) throws SQLException;
    void deleteProject(int id) throws SQLException;
    void updateTotalCost(int projectId, double totalCost) throws SQLException;
    void updateProjectStatus(int projectId, ProjectStatus status) throws SQLException;
}

