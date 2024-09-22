package com.dao;

import com.models.Component;

import java.sql.SQLException;
import java.util.List;

public interface ComponentRepository {
    List<Component> getComponentsByProjectId(int projectId) throws SQLException;
    void addComponent(Component component, int projectId) throws SQLException;
}

