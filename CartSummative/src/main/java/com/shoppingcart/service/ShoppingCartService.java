package com.shoppingcart.service;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.interfaces.CartOperations;
import com.shoppingcart.utility.Utils;

import java.util.*;


// Implements the CartOperations interface
public class ShoppingCartService implements CartOperations {
    // Store items using a map
    private  final Map<String, CartItem> cart = new HashMap<>();

    @Override // Add items to cart or updates if it already exists in cart
    public String addItem(String name, double price, int quantity) {
        if (quantity <= 0) {
            return "Quantity must be greater than 0.";
        }
        if (price <= 0) {
            return "Price must be at least $0.01.";
        }

        name = name.toLowerCase(); // Case formats to ensure Apple and apple are added together

        if (cart.containsKey(name)) {
            CartItem existing = cart.get(name);
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            cart.put(name, new CartItem(name, price, quantity));
        }
        // Return confirmation message for UI to display
        return String.format("Added %d x %s to cart.", quantity, Utils.capitalize(name));
    }

    @Override // Removes Items from cart or if it reaches 0
    public String removeItem(String name, int quantity) {
        if (quantity <= 0) {
            return "Enter a positive quantity to remove.";
        }
        name = name.toLowerCase(); // Case formats to ensure casing

        if (!cart.containsKey(name)) {
            return "Item not in cart.";
        }

        CartItem item = cart.get(name);
        if (quantity >= item.getQuantity()) {
            cart.remove(name);
            return Utils.capitalize(name) + " removed from cart.";
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            return String.format("Removed %d of %s. Remaining: %d",
                    quantity, Utils.capitalize(name), item.getQuantity());
        }
    }
    // Returns sorted list of items (A–Z) for UI display
    @Override
    public List<CartItem> getCartItems() {
        List<CartItem> itemList = new ArrayList<>(cart.values());
        itemList.sort(Comparator.comparing(CartItem::getName)); // Sort alphabetically
        return itemList;
    }

    // Returns true if the cart is empty (used before display or checkout)
    @Override
    public boolean isCartEmpty() {
        return cart.isEmpty();
    }

    // Calculates total, clears the cart, and returns total amount
    @Override
    public double checkout() {
        if (cart.isEmpty()) {
            return 0.0; // Nothing to check out
        }

        // Sum total of all items
        double total = cart.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        cart.clear(); // Empty cart after checkout
        return total;
    }

    // Optional: expose cart for testing or debugging
    @Override
    public Map<String, CartItem> getCart() {
        return cart;
    }
}

