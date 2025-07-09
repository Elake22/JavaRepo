package ui;

import model.Product;
import java.util.Scanner;

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
        System.out.println("ID       | Name             | Quantity | Price");
        System.out.println("-----------------------------------------------");
    }

    public static void printProductDetails(String id, String name, int quantity, double price) {
        System.out.println("Product Found:");
        System.out.printf("ID: %-10s\n", id);
        System.out.printf("Name: %-15s\n", name);
        System.out.printf("Quantity: %-5d\n", quantity);
        System.out.printf("Price: $%.2f\n", price);
    }

    public static void printProductRow(Product p) {
        System.out.printf("%-8s | %-15s | %-8d | $%.2f\n",
                p.getProductID(), p.getProductName(), p.getQuantity(), p.getPrice());
    }

    public static void printNotFoundMessage() {
        System.out.println("Product not found!");
    }

    public static void printCurrentDetails(Product product) {
        System.out.printf("Current Quantity: %d\n", product.getQuantity());
        System.out.printf("Current Price: $%.2f\n", product.getPrice());
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
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int promptInt(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    public static double promptDouble(String message) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Try again.");
            }
        }
    }
}
