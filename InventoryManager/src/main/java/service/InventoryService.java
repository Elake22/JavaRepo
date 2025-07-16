package service;

import data.InventoryRepository;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Service layer that handles business logic for inventory management
@Service
public class InventoryService {

    // Dependency on the repository interface, injected through constructor
    private final InventoryRepository repository;

    @Autowired// Injects the repository implementation
    public InventoryService(@Qualifier("CSVMemoryInventoryRepository") InventoryRepository repository) {
        this.repository = repository;
    }
    // Adds a product to the inventory (delegates to repository)
    public boolean addProduct(Product product) {
        // Reject invalid quantity or price
        if (product.getQuantity() < 0 || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {

            return false;
        }
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
    // Saves current inventory
    public boolean saveInventory(String filename) {
        return repository.saveToFile(filename);
    }
    // Loads saved inventory
    public boolean loadInventory(String filename) {
        return repository.loadFromFile(filename);
    }

}