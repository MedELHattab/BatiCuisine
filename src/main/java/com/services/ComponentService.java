package com.services;

import com.dao.ComponentRepository;
import com.dao.ComponentRepositoryImpl;
import com.models.Component;

import java.sql.SQLException;
import java.util.List;

public class ComponentService {
    private final ComponentRepository componentRepository;

    // Constructor that initializes the implementation of ComponentRepository
    public ComponentService() throws SQLException {
        this.componentRepository = new ComponentRepositoryImpl(); // Initialize with the implementation
    }

    public List<Component> getComponentsByProjectId(int projectId) throws SQLException {
        return componentRepository.getComponentsByProjectId(projectId);
    }

    public void addComponent(Component component, int projectId) throws SQLException {
        componentRepository.addComponent(component, projectId);
    }
}
