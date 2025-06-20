package com.shoppingcart;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.service.ShoppingCartService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    private ShoppingCartService cart;

    @BeforeEach //Resets cart to empty before each test
    public void setUp() {
        cart = new ShoppingCartService();
    }

    @Test // Test that the item is added to the cart
    public void testAddNewItem() {
        cart.addItem("apple", 2.0,2); // Adds 2 apples for $2 each
        Map<String, CartItem> cartItems = cart.getCart();
        assertTrue(cartItems.containsKey("apple"));       //Item should exist in cart
        assertEquals(2, cartItems.get("apple").getQuantity()); // Quantity should be 2
    }

    @Test // Test that adding same item update quantity
    public void testAddSameItemUpdatesQuantity() {
        cart.addItem("charcoal bag", 5, 2); // Adds 2 Charcoal bags
        cart.addItem("charcoal bag", 5, 1); // Adds one more
        assertEquals(3, cart.getCart().get("charcoal bag").getQuantity()); // Quantity 3
    }
    @Test // Test removing some of an item
    public void testRemoveItemPartialQuantity() {
        cart.addItem("water", 2.5, 2); // Adds 2 waters
        cart.removeItem("water",1); // Removes 1 water
        assertEquals(1, cart.getCart().get("water").getQuantity()); // 1 water should be left in cart
    }

    @Test // Test removing all of an item
    public void testRemoveItemFullQuantity() {
        cart.addItem("racecar", 10, 4); // Adds 4 racecars
        cart.removeItem("racecar", 4); // Removes 4 racecars
        assertFalse(cart.getCart().containsKey("racecar")); // Racecar stays removed
    }
    @Test // Ensure it does not remove into the negative
    public void testRemoveDoesNotGoNegative() {
        cart.addItem("book", 3, 5); // Adds 5 books
        cart.removeItem("book",10);  // Tries to remove 10 books
        assertFalse(cart.getCart().containsKey("book"));
    }

    @Test // Test Checkout operations
    public void testCeckout() {
        cart.addItem("croc", 20, 2);  // $40
        cart.addItem("watch", 50, 1); // $50
        cart.addItem("mouse", 10, 1); // $10
        double total = cart.checkout();    // Expected total $100
        assertEquals(100.0, total, 0.00); // Check total
        assertTrue(cart.getCart().isEmpty()); // Empty cart
    }

    @Test// Test checkout emptys cart
    public void testCheckoutEmptyCart() {
        double total = cart.checkout(); // No items in cart
        assertEquals(0.0, total, 0.00); // Total should be $0
    }


}
