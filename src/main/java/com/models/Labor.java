package com.models;

public class Labor extends Component {
    private double hourlyRate;
    private double hoursWorked;
    private double productivityFactor;

    public Labor(int id, String name, double hourlyRate, double hoursWorked, double productivityFactor) {
        super(id, name, 0); // Cost will be calculated later
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.productivityFactor = productivityFactor;
        this.cost = calculateCost();
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getProductivityFactor() {
        return productivityFactor;
    }

    public void setProductivityFactor(double productivityFactor) {
        this.productivityFactor = productivityFactor;
    }

    @Override
    public double calculateCost() {
        return hoursWorked * hourlyRate * productivityFactor;
    }
}

