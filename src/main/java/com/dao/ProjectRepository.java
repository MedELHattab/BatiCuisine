
package com.dao;

import com.models.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {
    void createProject(Project project) throws SQLException;
    Project getProjectById(int id) throws SQLException;
    List<Project> getAllProjects() throws SQLException;
    void updateProject(Project project) throws SQLException;
    void deleteProject(int id) throws SQLException;
}

