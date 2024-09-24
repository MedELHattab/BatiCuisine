package com.services;

import com.dao.ProjectRepository;
import com.dao.ProjectRepositoryImpl;
import com.models.Devis;
import com.models.Project;
import com.models.ProjectStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService() throws SQLException {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    public int createProject(Project project) throws SQLException {
        return projectRepository.createProject(project);
    }

    public Project getProjectById(int id) throws SQLException {
        return projectRepository.getProjectById(id);
    }

    public Map<Integer, Project> getAllProjects() throws SQLException {
        return projectRepository.getAllProjects();
    }

    public void updateProject(Project project) throws SQLException {
        projectRepository.updateProject(project);
    }

    public void deleteProject(int id) throws SQLException {
        projectRepository.deleteProject(id);
    }
    public void updateTotalCost(int projectId, double totalCost) throws SQLException{
        projectRepository.updateTotalCost(projectId, totalCost);
    };
    public void updateProjectStatus (int projectId, ProjectStatus ProjectStatus) throws SQLException {

        projectRepository.updateProjectStatus(projectId,ProjectStatus);
    }

}

