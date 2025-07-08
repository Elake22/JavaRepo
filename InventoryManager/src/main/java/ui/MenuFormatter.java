package ui;

public class MenuFormatter {

    // Prints the main header and menu options
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

    // Reusable section header
    public static void printSection(String title) {
        System.out.println("\n===== " + title + " =====");
    }

    // For product table headers (used in View Products)
    public static void printTableHeader() {
        System.out.println("ID   | Name           | Quantity | Price");
        System.out.println("------------------------------------------");
    }

    // For Search Product display
    public static void printProductDetails(String id, String name, int quantity, double price) {
        System.out.println("Product Found:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Price: $%.2f%n", price);
    }

    // For "Product not found"
    public static void printNotFoundMessage() {
        System.out.println("Product not found!");
    }

    // For Update Product prompts
    public static void printUpdatePrompt() {
        System.out.print("Enter New Quantity (or press Enter to skip): ");
        // prompt for quantity only
        System.out.print("Enter New Price (or press Enter to skip): ");
    }

    // For Delete Product confirmation
    public static void printDeleteConfirmationPrompt() {
        System.out.print("Are you sure you want to delete this product? (Y/N): ");
    }

    // For Save Inventory display
    public static void printSavingInventory() {
        printSection("Save Inventory");
        System.out.println("Saving inventory data...");
    }

    public static void printInventorySavedSuccess() {
        System.out.println("Inventory successfully saved to inventory.txt!");
    }

    public static void printInventorySaveError() {
        System.out.println("Error saving data!");
    }

    // For Load Inventory display
    public static void printLoadingInventory() {
        printSection("Load Inventory");
        System.out.println("Loading inventory data...");
    }

    public static void printInventoryLoadedSuccess() {
        System.out.println("Inventory successfully loaded from inventory.txt!");
    }

    public static void printInventoryLoadError() {
        System.out.println("Error loading data!");
    }

    public static void pressEnterToContinue() {
        System.out.println("\nPress Enter to return to the main menu...");
    }
}

