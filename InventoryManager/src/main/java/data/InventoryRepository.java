package data;

import model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository {
    boolean add(Product product);
    boolean update(String productId, Product updatedProduct);
    boolean remove(String productId);
    Optional<Product> findById(String productId);
    List<Product> findAll();
    boolean saveToFile(String filename);
    boolean loadFromFile(String filename);

}
