package domain;

import java.time.LocalDate;

public class Encounter {
    private int id;
    private String description;
    private LocalDate date;
    private EncounterType type;

    public Encounter(int id, String description, LocalDate date, EncounterType type) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.type = type;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EncounterType getType() {
        return type;
    }

    public void setType(EncounterType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Type: %s | Date: %s | Description: %s",
                id, type, date, description);
    }
}
