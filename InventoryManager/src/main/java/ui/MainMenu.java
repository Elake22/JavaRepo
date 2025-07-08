package ui;

import model.Product;
import service.InventoryService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainMenu {
    private final InventoryService service;
    private final Scanner scanner;

    public MainMenu(InventoryService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

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
                    if (service.saveInventory("src/inventory.txt")) {
                        MenuFormatter.printInventorySavedSuccess();
                    } else {
                        MenuFormatter.printInventorySaveError();
                    }
                    MenuFormatter.pressEnterToContinue();
                    scanner.nextLine();
                }
                case "7" -> {
                    MenuFormatter.printLoadingInventory();
                    if (service.loadInventory("src/inventory.txt")) {
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
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Product product = new Product(id, name, qty, price);
        if (service.addProduct(product)) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Product already exists or input is invalid.");
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void viewProducts() {
        MenuFormatter.printSection("Inventory List");
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            MenuFormatter.printTableHeader();
            for (Product product : products) {
                System.out.println(product);
            }
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void searchProduct() {
        MenuFormatter.printSection("Search Product");
        System.out.print("Enter Product ID or Name: ");
        String input = scanner.nextLine();

        Optional<Product> result = service.getAllProducts().stream()
                .filter(p -> p.getProductID().equalsIgnoreCase(input) || p.getProductName().equalsIgnoreCase(input))
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
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();

        Optional<Product> optional = service.findProductById(id);
        if (optional.isEmpty()) {
            MenuFormatter.printNotFoundMessage();
            return;
        }

        Product current = optional.get();
        System.out.println("Current Details:");
        System.out.println("Name: " + current.getProductName());
        System.out.println("Quantity: " + current.getQuantity());
        System.out.printf("Price: $%.2f%n", current.getPrice());

        System.out.print("Enter New Quantity (or press Enter to skip): ");
        String qtyInput = scanner.nextLine();
        System.out.print("Enter New Price (or press Enter to skip): ");
        String priceInput = scanner.nextLine();

        int newQty = qtyInput.isBlank() ? current.getQuantity() : Integer.parseInt(qtyInput);
        double newPrice = priceInput.isBlank() ? current.getPrice() : Double.parseDouble(priceInput);

        Product updated = new Product(id, current.getProductName(), newQty, newPrice);
        service.updateProduct(id, updated);
        System.out.println("Product updated successfully!");
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void deleteProduct() {
        MenuFormatter.printSection("Delete Product");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();

        Optional<Product> optional = service.findProductById(id);
        if (optional.isEmpty()) {
            MenuFormatter.printNotFoundMessage();
            return;
        }

        MenuFormatter.printDeleteConfirmationPrompt();
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            service.removeProduct(id);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void saveInventory() {
        service.saveInventory("src/inventory.txt");
        MenuFormatter.printSection("Save Inventory");
        System.out.println("Saving inventory data...");
        // Stub: Logic to save inventory to file will go here
        System.out.println("Inventory successfully saved to inventory.txt!");
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }

    private void loadInventory() {
        service.loadInventory("src/inventory.txt");
        MenuFormatter.printSection("Load Inventory");
        System.out.println("Loading inventory data...");
        // Stub: Logic to load inventory from file will go here
        System.out.println("Inventory successfully loaded from inventory.txt!");
        MenuFormatter.pressEnterToContinue();
        scanner.nextLine();
    }
}
