package ui;

import model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuFormatter {

    public static void printMainMenu() {
        System.out.println("===== Inventory Manager =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Save Inventory to File");
        System.out.println("7. Load Inventory from File");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void printSection(String title) {
        System.out.println("\n===== " + title + " =====");
    }

    public static void printTableHeader() {
        System.out.println("ID       | Name            | Quantity | Price");
        System.out.println("-----------------------------------------------");
    }

    public static void printProductDetails(String id, String name, int quantity, BigDecimal price) {
        System.out.println("Product Found:");
        System.out.printf("ID: %-10s\n", id);
        System.out.printf("Name: %-15s\n", name);
        System.out.printf("Quantity: %-5d\n", quantity);
        System.out.printf("Price: $%s%n", price);
    }

    public static void printProductRow(Product p) {
        System.out.printf("%-8s | %-15s | %-8d | $%s%n",
                p.getProductID(), p.getProductName(), p.getQuantity(), p.getPrice());
    }

    public static void printNotFoundMessage() {
        System.out.println("Product not found!");
    }

    public static void printCurrentDetails(Product product) {
        System.out.printf("Current Quantity: %d\n", product.getQuantity());
        System.out.printf("Current Price: $%s%n", product.getPrice());
    }

    public static void printDeleteConfirmationPrompt() {
        System.out.print("Are you sure you want to delete this product? (Y/N): ");
    }

    public static void printSuccessMessage(String message) {
        System.out.println(message);
    }

    public static void printSuccess(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public static void printCancelled() {
        System.out.println("Operation cancelled.");
    }

    public static void printInvalidChoice() {
        System.out.println("Invalid selection. Please try again.");
    }

    public static void printNoProducts() {
        System.out.println("No products found.");
    }

    public static void pressEnterToContinue() {
        System.out.println("\nPress Enter to return to the main menu...");
    }

    public static void printSavingInventory() {
        System.out.println("Saving inventory data...");
    }

    public static void printInventorySavedSuccess() {
        System.out.println("Inventory successfully saved to inventory.txt!");
    }

    public static void printInventorySaveError() {
        System.out.println("Error saving inventory to file.");
    }

    public static void printLoadingInventory() {
        System.out.println("Loading inventory data...");
    }

    public static void printInventoryLoadedSuccess() {
        System.out.println("Inventory successfully loaded from inventory.txt!");
    }

    public static void printInventoryLoadError() {
        System.out.println("Error loading inventory from file.");
    }

    public static String promptInput(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return formatProductName(input);
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // Required integer input
    public static int promptInt(String message) {
        return promptInt(message, false, 0);
    }

    // Optional integer input with default fallback for updates
    public static int promptInt(String message, boolean allowBlank, int defaultValue) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (allowBlank && input.isEmpty()) {
                return defaultValue; // Return default if blank and allowed
            }
            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("Quantity cannot be negative. Try again.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    // Required BigDecimal input
    public static BigDecimal promptBigDecimal(String message) {
        return promptBigDecimal(message, false, BigDecimal.valueOf(0.0));
    }

    // Optional BigDecimal input with default fallback for updates
    public static BigDecimal promptBigDecimal(String message, boolean allowBlank, BigDecimal defaultValue) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (allowBlank && input.isBlank()) {
                return defaultValue; // Keep current price
            }

            try {
                BigDecimal value = new BigDecimal(input);
                if (value.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Price cannot be negative. Try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Try again.");
            }
        }
    }


    public static String formatProductName(String input) {
        if (input == null || input.isBlank()) return input;
        return Arrays.stream(input.trim().split("\\s+"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}