package com.models;

public class Material extends Component {
    private double quantity;
    private double unitCost;
    private double transportCost;
    private double qualityCoefficient;
    private double tvaRate;

    public Material(int id, String name, double quantity, double unitCost, double transportCost, double qualityCoefficient, double tvaRate) {
        super(id, name, unitCost * quantity);  // Initial cost calculation based on unit cost and quantity
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
        this.tvaRate = tvaRate;
    }

    @Override
    public double calculateCost() {
        // Calculate total cost based on quantity, unit cost, transport cost, and quality coefficient
        return (unitCost * quantity * qualityCoefficient) + transportCost;
    }

    // Getters and Setters
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

    public double getTvaRate() {
        return tvaRate;
    }
}
