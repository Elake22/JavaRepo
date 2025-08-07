package com.example.demo.model;

import java.math.BigDecimal;

public class Booking {

    private int id;
    private int customerId;
    private int serviceId;
    private int mechanicId;
    private String timePreference;
    private BigDecimal estimatedTotal; // switched from double to BigDecimal

    public Booking() {
    }

    public Booking(int id, int customerId, int serviceId, int mechanicId, String timePreference, BigDecimal estimatedTotal) {
        this.id = id;
        this.customerId = customerId;
        this.serviceId = serviceId;
        this.mechanicId = mechanicId;
        this.timePreference = timePreference;
        this.estimatedTotal = estimatedTotal;
    }

    // --- Getters and Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getTimePreference() {
        return timePreference;
    }

    public void setTimePreference(String timePreference) {
        this.timePreference = timePreference;
    }

    public BigDecimal getEstimatedTotal() {
        return estimatedTotal;
    }

    public void setEstimatedTotal(BigDecimal estimatedTotal) {
        this.estimatedTotal = estimatedTotal;
    }
    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", serviceId=" + serviceId +
                ", mechanicId=" + mechanicId +
                ", timePreference='" + timePreference + '\'' +
                ", estimatedTotal=" + estimatedTotal +
                '}';
    }
}
