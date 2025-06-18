package com.shoppingcart.interfaces;


// Core cart operations: add, remove, display, checkout
public interface CartOperations {

    // Adds item
    void addItem(String name, double price, int quantity);

    // Removes item
    void removeItem(String name, int quantity);

    // Display cart
    void displayCart();

    // Finish checkout, returns total, clears cart
    double checkout();

}
