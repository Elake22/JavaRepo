package data;

import model.Product;

import java.util.*;

// An in-memory implementation of InventoryRepository using a HashMap
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
    
}

