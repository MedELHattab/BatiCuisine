package com.models;

public class Project {

    private int id;
    private String projectName;
    private double surfaceArea;
    private double profitMargin;
    private double totalCost;
    private ProjectStatus projectStatus;
    private int clientID;

    public Project(int id, String projectName, double surfaceArea, double profitMargin, double totalCost, ProjectStatus projectStatus, int clientID) {
        this.id = id;
        this.projectName = projectName;
        this.surfaceArea = surfaceArea;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.projectStatus = projectStatus;
        this.clientID = clientID;
    }

    // Getters

    public ProjectStatus getProjectStatus() {
        return projectStatus; // This should return the enum
    }

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getClientID() {
        return clientID;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}

