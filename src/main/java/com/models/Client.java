package com.models;

public class Client {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private boolean isProfessional;

    public Client(int id, String name, String address, String phoneNumber, boolean isProfessional) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isProfessional = isProfessional;
    }

    // Getters and setters
    public int getClientId() { return id; }
    public void setClientId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhone(String phone) { this.phoneNumber = phone; }
    public boolean isProfessional() { return isProfessional; }
    public void setProfessional(boolean isProfessional) { this.isProfessional = isProfessional; }
}

