package com.services;

import com.dao.ProjectRepository;
import com.dao.ProjectRepositoryImpl;
import com.models.Project;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService() throws SQLException {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    public void createProject(Project project) throws SQLException {
        projectRepository.createProject(project);
    }

    public Project getProjectById(int id) throws SQLException {
        return projectRepository.getProjectById(id);
    }

    public List<Project> getAllProjects() throws SQLException {
        return projectRepository.getAllProjects();
    }

    public void updateProject(Project project) throws SQLException {
        projectRepository.updateProject(project);
    }

    public void deleteProject(int id) throws SQLException {
        projectRepository.deleteProject(id);
    }
}

