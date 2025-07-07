package data;

import model.Product;

import java.util.*;

// An in-memory implementation of InventoryRepository using a HashMap
public abstract class InMemoryInventoryRepository implements InventoryRepository {

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
}

