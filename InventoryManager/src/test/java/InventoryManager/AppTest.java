package InventoryManager;

import model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class AppTest {

    @Test
    void testProductCreation() {
        Product product = new Product("A001", "Notebook", 10, 2.50);
        assertEquals("A001", product.getProductID());
        assertEquals("Notebook",product.getProductName());
        assertEquals(10, product.getQuantity());
        assertEquals(2.50, product.getPrice());
    }

}
