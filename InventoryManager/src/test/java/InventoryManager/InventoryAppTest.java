package InventoryManager;

import model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import service.InventoryService;
import ui.MainMenu;

import java.io.File;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InventoryAppTest {

    private static final String TEST_FILE = "test_inventory_flow.txt";

    @Autowired
    private InventoryService service;

    @Autowired
    private MainMenu mainMenu;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
        assertThat(mainMenu).isNotNull();
    }

    @Test
    void fullInventoryFlowShouldWork() {
        Product product = new Product("P100", "IntegrationTest", 5, new BigDecimal("9.99"));


        // Add product
        assertTrue(service.addProduct(product));

        // Save to file
        assertTrue(service.saveInventory(TEST_FILE));

        // Remove and verify it's gone
        assertTrue(service.removeProduct("P100"));
        assertFalse(service.findProductById("P100").isPresent());

        // Reload and verify it's back
        assertTrue(service.loadInventory(TEST_FILE));
        assertTrue(service.findProductById("P100").isPresent());
    }

    @AfterEach
    void cleanUp() {
        new File(TEST_FILE).delete();
    }
}
