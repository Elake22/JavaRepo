package com.example.demo.model;


// Represents a mechanic who can be assigned to a service appointment.
public class Mechanic {

    private int id; // Unique ID
    private String name; // Mechanic's full name
    private int experienceYears; // Years of experience

    public Mechanic() {
    }

    public Mechanic(int id, String name, int experienceYears) {
        this.id = id;
        this.name = name;
        this.experienceYears = experienceYears;
    }

    // --- Getters and Setters ---

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

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
}
