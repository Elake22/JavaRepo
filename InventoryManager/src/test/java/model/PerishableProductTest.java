package model;

import model.PerishableProduct;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

// Perishable Product model test
public class PerishableProductTest {

    @Test
    void testPerishableProductCreation() {
        LocalDate date = LocalDate.of(2025, 12, 31);
        PerishableProduct milk = new PerishableProduct(4, "Milk", 30, 3.99, date);

        assertEquals(4, milk.getProductID());
        assertEquals("Milk", milk.getProductName());
        assertEquals(30, milk.getQuantity());
        assertEquals(3.99, milk.getPrice());
        assertEquals(date, milk.getExpirationDate());
    }

    @Test
    void testExpirationDateSetter() {
        LocalDate oldDate = LocalDate.of(2025, 1, 1);
        LocalDate newDate = LocalDate.of(2025, 2, 1);
        PerishableProduct yogurt = new PerishableProduct(5, "Yogurt", 20, 2.49, oldDate);

        yogurt.setExpirationDate(newDate);
        assertEquals(newDate, yogurt.getExpirationDate());
    }

    @Test // Create perishable item and set expiration
    void testToStringIncludesExpiration() {
        LocalDate date = LocalDate.of(2025, 7, 15);
        PerishableProduct cheese = new PerishableProduct(6, "Cheese", 12, 5.49, date);
        String output = cheese.toString();

        assertTrue(output.contains("Cheese"));
        assertTrue(output.contains("2025-07-15"));
    }
}
