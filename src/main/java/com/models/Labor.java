package com.models;

public class Labor extends Component {
    private String laborType;
    private double hourlyRate;
    private double workHours;
    private double workerProductivity;
    private double tvaRate;


    public Labor(int id, String name, String laborType, double hourlyRate, double workHours, double workerProductivity, double tvaRate) {
        super(id, name, hourlyRate * workHours * workerProductivity);  // Initial cost calculation
        this.laborType = laborType;
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
        this.tvaRate = tvaRate;
    }

    @Override
    public double calculateCost() {
        // Calculate labor cost based on hourly rate, work hours, and worker productivity
        return hourlyRate * workHours * workerProductivity;
    }

    // Getters and Setters
    public String getLaborType() {
        return laborType;
    }

    public void setLaborType(String laborType) {
        this.laborType = laborType;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }

    public double getTvaRate() {
       return tvaRate;
    }

    public double getProductivity() {
    return workerProductivity;
    }
}
