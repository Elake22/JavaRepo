package com.example.demo.model;


// This class represents a customer in the garage booking system.
public class Customer {

    // Unique identifier for each customer
    private int id;

    // First name of the customer
    private String firstName;

    // Last name of the customer
    private String lastName;

    // Email address of the customer
    private String email;

    // Phone number of the customer
    private String phone;

    // Model of the car the customer owns
    private String carModel;

    public Customer() {
    }
    public Customer(int id, String firstName, String lastName, String email, String phone, String carModel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.carModel = carModel;
    }

    // --- Getters and Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", carModel='" + carModel + '\'' +
                '}';
    }
}
