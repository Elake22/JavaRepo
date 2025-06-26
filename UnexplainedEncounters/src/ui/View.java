package ui;

import domain.Encounter;
import domain.EncounterType;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);

    public void displayHeader(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    public void displayMenu() {
        System.out.println("1. Display Encounters by Type");
        System.out.println("2. Add a New Encounter");
        System.out.println("3. Update an Encounter");
        System.out.println("4. Delete an Encounter");
        System.out.println("5. Exit");

    }

    public int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a number.");
            return readInt();
        }
    }

    public EncounterType readEncounterType() {
        System.out.println("\nSelect an Encounter Type:");
        EncounterType[] types = EncounterType.values();

        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i]);
        }

        System.out.print("Enter choice: ");
        int choice = readInt();

        if (choice < 1 || choice > types.length) {
            System.out.println("Invalid selection.");
            return readEncounterType();
        }

        return types[choice - 1];
    }

    public void displayEncounters(List<Encounter> encounters) {
        if (encounters == null || encounters.isEmpty()) {
            System.out.println("No encounters found.");
            return;
        }

        for (Encounter e : encounters) {
            System.out.println(e);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    public Encounter editEncounter(Encounter original) {
        System.out.println("\nUpdating Encounter ID: " + original.getId());

        System.out.println("Current Description: " + original.getDescription());
        System.out.print("New Description (leave blank to keep): ");
        String description = scanner.nextLine();
        if (description.isBlank()) {
            description = original.getDescription();
        }

        System.out.println("Current Date: " + original.getDate());
        System.out.print("New Date (yyyy-MM-dd) or leave blank: ");
        String dateInput = scanner.nextLine();
        LocalDate date = dateInput.isBlank() ? original.getDate() : LocalDate.parse(dateInput);

        EncounterType newType = readEncounterType();

        return new Encounter(original.getId(), description, date, newType);
    }
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public LocalDate readDate(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            System.out.println("Invalid date format. Try again.");
            return readDate(prompt);
        }
    }


}
