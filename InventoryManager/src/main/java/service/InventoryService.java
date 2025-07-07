package service;

import data.InventoryRepository;
import model.Product;

import java.util.List;
import java.util.Optional;

// Service layer that handles business logic for inventory management
public class InventoryService {

    // Dependency on the repository interface, injected through constructor
    private final InventoryRepository repository;

    // Constructor injects the repository implementation
    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }
    // Adds a product to the inventory (delegates to repository)
    public boolean addProduct(Product product) {
        return repository.add(product);
    }
    // Updates an existing product by ID (delegates to repository)
    public boolean updateProduct(String productID, Product updatedProduct) {
        return repository.update(productID, updatedProduct);
    }
    // Removes a product from the inventory by ID
    public boolean removeProduct(String productId) {
        return repository.remove(productId);
    }
    // Finds a single product by its ID
    public Optional<Product> findProductById(String productId) {
        return repository.findById(productId);
    }
    // Returns a list of all products in the inventory
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}