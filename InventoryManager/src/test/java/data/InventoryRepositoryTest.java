package data;

import model.Product;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryRepositoryTest {

    private InMemoryInventoryRepository repository;
    private static final String TEST_FILE = "test_inventory.txt"; // File used for save/load tests

    @BeforeEach
        // Create a fresh repository before each test
    void setUp() {
        repository = new InMemoryInventoryRepository();
    }

    @AfterEach
        // Remove the test file after each test
    void cleanUp() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testSaveAndLoad() {
        // Add a product to the current repository
        Product product = new Product("P001", "Pen", 10, 1.50);
        assertTrue(repository.add(product));

        // Save the repository data to a file
        assertTrue(repository.saveToFile(TEST_FILE));

        // Create a new repository and load data from the file
        InMemoryInventoryRepository loadedRepo = new InMemoryInventoryRepository();
        assertTrue(loadedRepo.loadFromFile(TEST_FILE));

        // Verify the loaded data matches what was saved
        assertEquals(1, loadedRepo.findAll().size());
        assertEquals("Pen", loadedRepo.findById("P001").get().getProductName());
    }
}

