package com.models;

public class Material extends Component {
    private double quantity;
    private double unitCost;
    private double transportCost;
    private double qualityCoefficient;

    public Material(int id, String name, double quantity, double unitCost, double transportCost, double qualityCoefficient) {
        super(id, name, 0); // Cost will be calculated later
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
        this.cost = calculateCost();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }

    public void setQualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }

    @Override
    public double calculateCost() {
        return (quantity * unitCost * qualityCoefficient) + transportCost;
    }
}

