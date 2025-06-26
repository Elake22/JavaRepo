package ui;

import domain.Encounter;
import domain.EncounterService;
import domain.EncounterType;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private final View view;
    private final EncounterService service;
    private boolean running = true;

    public Controller(View view, EncounterService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        view.displayHeader("Welcome to Unexplained Encounters");

        while (running) {
            view.displayMenu();
            int choice = view.readInt();

            switch (choice) {
                case 1 -> displayByType();
                case 2 -> addEncounter();
                case 3 -> updateEncounter();
                case 4 -> deleteEncounter();
                case 5 -> exit();
                default -> view.displayMessage("Invalid choice. Try again.");
            }

        }
    }

    private void displayByType() {
        EncounterType type = view.readEncounterType();
        List<Encounter> encounters = service.findByType(type);

        view.displayHeader("Encounters: " + type);
        view.displayEncounters(encounters);
    }

    private void exit() {
        view.displayMessage("Goodbye!");
        running = false;
    }
    private void updateEncounter() {
        List<Encounter> all = service.findAll();
        view.displayEncounters(all);

        view.displayMessage("Enter ID of encounter to update:");
        int id = view.readInt();

        Encounter existing = service.findById(id);
        if (existing == null) {
            view.displayMessage("Encounter not found.");
            return;
        }

        Encounter updated = view.editEncounter(existing); // we'll build this in View next
        boolean success = service.update(updated);

        if (success) {
            view.displayMessage("✅ Encounter updated successfully.");
        } else {
            view.displayMessage("❌ Update failed.");
        }
    }
    private void deleteEncounter() {
        List<Encounter> all = service.findAll();
        view.displayEncounters(all);

        view.displayMessage("Enter the ID of the encounter to delete:");
        int id = view.readInt();

        boolean success = service.deleteById(id);

        if (success) {
            view.displayMessage("✅ Encounter deleted.");
        } else {
            view.displayMessage("❌ Encounter not found.");
        }
    }
    private void addEncounter() {
        view.displayHeader("Add a New Encounter");

        String description = view.readString("Enter description: ");
        EncounterType type = view.readEncounterType();
        LocalDate date = view.readDate("Enter date (YYYY-MM-DD): ");

        Encounter newEncounter = new Encounter(0, description, date, type); // ID will be set in repo
        boolean success = service.add(newEncounter);

        if (success) {
            view.displayMessage("✅ Encounter added successfully!");
        } else {
            view.displayMessage("❌ Failed to add encounter.");
        }
    }



}
