package com.models;

public abstract class Component {
    protected int id;
    protected String name;
    protected double cost;

    public Component(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // Abstract method for cost calculation to be implemented in subclasses
    public abstract double calculateCost();
}
