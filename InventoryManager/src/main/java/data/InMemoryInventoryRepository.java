package data;

import model.Product;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

// An in-memory implementation of InventoryRepository
@Repository // Tells Spring it is ready for dependency injection
public class InMemoryInventoryRepository implements InventoryRepository {

    // Stores products in a map using productID as the key
    private final Map<String, Product> inventory = new HashMap<>();

    // Adds a new product if it doesn't already exist in the inventory
    @Override
    public boolean add(Product product) {
        if (inventory.containsKey(product.getProductID())) return false; // Duplicate check
        inventory.put(product.getProductID(), product); // Add to inventory
        return true;
    }

    // Updates an existing product by its ID
    @Override
    public boolean update(String productId, Product updatedProduct) {
        if (!inventory.containsKey(productId)) return false; // Cannot update non-existent product
        inventory.put(productId, updatedProduct); // Replace with updated product
        return true;
    }
    // Removes a product by ID, returns true if removed successfully
    @Override
    public boolean remove(String productId) {
        return inventory.remove(productId) != null;
    }
    // Finds a product by its ID
    @Override
    public Optional<Product> findById(String productId) {
        return Optional.ofNullable(inventory.get(productId));
    }
    // Returns all products in the inventory as a list
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(inventory.values());
    }
    // Save/writes to inventory file
    @Override
    public boolean saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Product product : inventory.values()) {
                writer.printf("%s,%s,%d,%.2f%n",
                        product.getProductID(),
                        product.getProductName(),
                        product.getQuantity(),
                        product.getPrice());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Loads from save file
    @Override
    public boolean loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            inventory.clear(); // Clear current memory first
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    int qty = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    inventory.put(id, new Product(id, name, qty, price));
                }
            }
            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
        }
    }

}

