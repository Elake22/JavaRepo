package view;


import java.util.List;
import java.util.Scanner;


public class LibraryView {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\nWelcome to the Library Management System");
        System.out.println("========================================");
        System.out.println("Main Menu");
        System.out.println("=========");
        System.out.println("0. Exit");
        System.out.println("1. Find Books by Category");
        System.out.println("2. Add a Book");
        System.out.println("3. Update a Book");
        System.out.println("4. Remove a Book");
        System.out.print("Select [0-4]: ");
    }

    public int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Invalid input
        }
    }
    public String prompt(String promptText) {
        System.out.print(promptText + ": ");
        return scanner.nextLine().trim();
    }
    public void showBooks(List<String> books, String label) {
        System.out.println("\n" + label + ":");
        if (books.isEmpty()) {
            System.out.println("  (None)");
        } else {
            for (String title : books) {
                System.out.println("  - " + title);
            }
        }
    }
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}


