package service;

import data.InMemoryInventoryRepository;
import model.Product;
import org.junit.jupiter.api.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    private InventoryService service;

    @BeforeEach
    void setUp() { // Inject a fresh in-memory repository into the service before each test
        service = new InventoryService(new InMemoryInventoryRepository());
    }
    @Test
    void testAddProduct() { // Add a new product and check if the operation was successful
        Product product = new Product("P001", "Notebook", 10, 2.99);
        assertTrue(service.addProduct(product));
    }
    @Test
    void testAddDuplicateProductFails() { // Add the same product twice; second attempt should fail
        Product product = new Product("P002", "Pen", 20, 1.50);
        service.addProduct(product);
        assertFalse(service.addProduct(product));
    }
    @Test
    void testRemoveProduct() {
        // Add and then remove a product, and confirm it's gone
        Product product = new Product("P004", "Eraser", 3, 0.50);
        service.addProduct(product);
        assertTrue(service.removeProduct("P004"));
        assertTrue(service.findProductById("P004").isEmpty());
    }
    @Test
    void testUpdateProduct() {
        // Add a product, update its quantity and price, then verify the update
        Product original = new Product("P003", "Marker", 5, 0.99);
        service.addProduct(original);
        Product updated = new Product("P003", "Marker", 8, 1.25);
        assertTrue(service.updateProduct("P003", updated));
        assertEquals(8, service.findProductById("P003").get().getQuantity());
    }
    @Test
    void testFindProductById() {
        // Add a product and search for it using its ID
        Product product = new Product("P005", "Glue", 6, 1.10);
        service.addProduct(product);
        Optional<Product> result = service.findProductById("P005");
        assertTrue(result.isPresent());
        assertEquals("Glue", result.get().getProductName());
    }

    @Test
    void testGetAllProducts() {
        // Add two products and verify that both are returned in the list
        service.addProduct(new Product("P006", "Scissors", 4, 2.75));
        service.addProduct(new Product("P007", "Stapler", 2, 4.99));
        assertEquals(2, service.getAllProducts().size());
    }
    @Test
    void shouldRejectNegativeQuantity() {
        Product product = new Product("P008", "Negative Item", -5, 10.0);
        assertFalse(service.addProduct(product));
    }

    @Test
    void shouldRejectNegativePrice() {
        Product product = new Product("P009", "Negative Price", 5, -10.0);
        assertFalse(service.addProduct(product));
    }

}
