package org.example.service;

import org.example.model.Item;
import org.example.model.OrderItem;
import org.example.view.ConsoleIO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemFormatterTest {

    @Test
    public void testFormatOrderItem_validInput() {
        // Arrange
        Item item = new Item();
        item.setItemName("Mozzarella Sticks");

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(2);
        orderItem.setPrice(new BigDecimal("6.00"));

        // Act
        String result = ConsoleIO.OrderItemFormatter.formatOrderItem(orderItem);

        // Assert
        assertTrue(result.contains("Mozzarella Sticks"));
        assertTrue(result.contains("$6.00") || result.contains("6.00")); // handles different locales
        assertTrue(result.contains("$12.00") || result.contains("12.00"));
    }

    @Test
    public void testFormatOrderItem_nullPrice() {
        // Arrange
        Item item = new Item();
        item.setItemName("Wings");

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setQuantity(3);
        orderItem.setPrice(null);

        // Act
        String result = ConsoleIO.OrderItemFormatter.formatOrderItem(orderItem);

        // Assert
        assertTrue(result.contains("Wings"));
        assertTrue(result.contains("$0.00") || result.contains("0.00"));
    }
}
