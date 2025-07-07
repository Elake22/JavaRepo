package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Product model test
public class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product("A001", "Notebook", 10, 2.50);
        assertEquals("A001", product.getProductID());
        assertEquals("Notebook",product.getProductName());
        assertEquals(10, product.getQuantity());
        assertEquals(2.50, product.getPrice());
    }
    @Test // Test Name, quantity and price
    void testSetters() {
        Product product = new Product("A002", "Pen", 20, 1.00);
        product.setProductName("Marker");
        product.setQuantity(15);
        product.setPrice(1.50);

        assertEquals("Marker", product.getProductName());
        assertEquals(15, product.getQuantity());
        assertEquals(1.50, product.getPrice());
    }
    @Test // Create a product and output should be Product name and price
    void testToString() {
        Product product = new Product("A003","Eraser", 5, 0.75);
        String output = product.toString();

        assertTrue(output.contains("Eraser"));   // Output should include product name
        assertTrue(output.contains("0.75"));     // Output should include price

    }

}
