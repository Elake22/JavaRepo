package com.shoppingcart.interfaces;

import com.shoppingcart.model.CartItem;
import java.util.List;
import java.util.Map;


// Core cart operations: add, remove, display, checkout
public interface CartOperations {

    // Adds item
    String addItem(String name, double price, int quantity);

    // Removes item
    String removeItem(String name, int quantity);

    // Returns sorted list of items (A–Z) for UI display
    List<CartItem> getCartItems();

    // Returns true if cart is empty
    boolean isCartEmpty();

    // Finish checkout, returns total, clears cart
    double checkout();

    // Exposes cart map for testing
    Map<String, CartItem> getCart();

}
