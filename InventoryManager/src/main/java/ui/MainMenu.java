package ui;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.InventoryService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainMenu {
    private final InventoryService service;
    private final Scanner scanner;

    @Autowired
    public MainMenu(InventoryService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }
    private static final String FILE_PATH = "src/inventory.txt";


    public void run() {
        boolean running = true;
        while (running) {
            MenuFormatter.printMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addProduct();
                case "2" -> viewProducts();
                case "3" -> searchProduct();
                case "4" -> updateProduct();
                case "5" -> deleteProduct();
                case "6" -> {
                    MenuFormatter.printSavingInventory();
                    if (service.saveInventory(FILE_PATH)) {
                        MenuFormatter.printInventorySavedSuccess();
                    } else {
                        MenuFormatter.printInventorySaveError();
                    }
                    MenuFormatter.pressEnterToContinue();
                    scanner.nextLine();
                }
                case "7" -> {
                    MenuFormatter.printLoadingInventory();
                    if (service.loadInventory(FILE_PATH)) {
                        MenuFormatter.printInventoryLoadedSuccess();
                    } else {
                        MenuFormatter.printInventoryLoadError();
                    }
                    MenuFormatter.pressEnterToContinue();
                    scanner.nextLine();
                }

                case "8" -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addProduct() {
        MenuFormatter.printSection("Add Product");
        String id = MenuFormatter.promptInput("Enter Product ID: ");
        String nameRaw = MenuFormatter.promptInput("Enter Product Name: ");
        String name = MenuFormatter.formatProductName(nameRaw);
        int qty = MenuFormatter.promptInt("Enter Quantity: ");
        double price = MenuFormatter.promptDouble("Enter Price: ");

        Product product = new Product(id, name, qty, price);
        if (service.addProduct(product)) {
            MenuFormatter.printSuccessMessage("Product added successfully!");
        } else {
            MenuFormatter.printErrorMessage("Product already exists or input is invalid.");
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void viewProducts() {
        MenuFormatter.printSection("Inventory List");
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            MenuFormatter.printNoProducts();
        } else {
            MenuFormatter.printTableHeader();
            for (Product product : products) {
                MenuFormatter.printProductRow(product);
            }
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void searchProduct() {
        MenuFormatter.printSection("Search Product");
        String input = MenuFormatter.promptInput("Enter Product ID or Name: ");

        Optional<Product> result = service.getAllProducts().stream()
                .filter(p -> p.getProductID().equalsIgnoreCase(input)
                        || p.getProductName().equalsIgnoreCase(input))
                .findFirst();

        if (result.isPresent()) {
            Product p = result.get();
            MenuFormatter.printProductDetails(p.getProductID(), p.getProductName(), p.getQuantity(), p.getPrice());
        } else {
            MenuFormatter.printNotFoundMessage();
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void updateProduct() {
        MenuFormatter.printSection("Update Product");
        String id = MenuFormatter.promptInput("Enter Product ID: ");

        // Try to find product by ID
        Optional<Product> optional = service.findProductById(id);
        if (optional.isEmpty()) {
            MenuFormatter.printNotFoundMessage();
            return;
        }
        // Product found — display current info
        Product current = optional.get();
        MenuFormatter.printProductDetails(current.getProductID(), current.getProductName(), current.getQuantity(), current.getPrice());

        // Prompt for new values (blank input keeps current values)
        int newQty = MenuFormatter.promptInt("Enter New Quantity (or press Enter to keep current): ", true, current.getQuantity());
        double newPrice = MenuFormatter.promptDouble("Enter New Price (or press Enter to keep current): ", true, current.getPrice());

        // Reformat product name to ensure consistent style (capitalize)
        String formattedName = MenuFormatter.formatProductName(current.getProductName());

        // Create updated product object
        Product updated = new Product(id, formattedName, newQty, newPrice);

        service.updateProduct(id, updated);
        MenuFormatter.printSuccess("Product updated successfully!");
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void deleteProduct() {
        MenuFormatter.printSection("Delete Product");
        String id = MenuFormatter.promptInput("Enter Product ID: ");

        Optional<Product> optional = service.findProductById(id);
        if (optional.isEmpty()) {
            MenuFormatter.printNotFoundMessage();
            return;
        }

        MenuFormatter.printDeleteConfirmationPrompt();
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            service.removeProduct(id);
            MenuFormatter.printSuccess("Product deleted successfully!");
        } else {
            MenuFormatter.printCancelled();
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void saveInventory() {
        MenuFormatter.printSection("Save Inventory");
        boolean success = service.saveInventory(FILE_PATH);
        if (success) {
            MenuFormatter.printSuccessMessage("Inventory save");
        } else {
            MenuFormatter.printErrorMessage("Inventory save");
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void loadInventory() {
        MenuFormatter.printSection("Load Inventory");
        boolean success = service.loadInventory(FILE_PATH);
        if (success) {
            MenuFormatter.printSuccessMessage("Inventory load");
        } else {
            MenuFormatter.printErrorMessage("Inventory load");
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }
}

