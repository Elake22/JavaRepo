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
    public void addItem(String name, double price, int quantity) {
        name = name.toLowerCase(); // Case formats to ensure Apple and apple are added together

        if (cart.containsKey(name)) {
            CartItem existing = cart.get(name);
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            cart.put(name, new CartItem(name, price, quantity));
        }
        System.out.printf("Added %d x %s to cart.\n", quantity, name);
    }

    @Override // Removes Items from cart or if it reaches 0
    public void  removeItem(String name, int quantity) {
        name = name.toLowerCase(); // Case formats to ensure casing

        if (!cart.containsKey(name)) {
            System.out.println("Item not in cart.");
            return;
        }

        CartItem item = cart.get(name);
        if (quantity >= item.getQuantity()) {
            cart.remove(name);
            System.out.println("Item removed from cart.");
        } else {
            item.setQuantity(item.getQuantity() - quantity);
            System.out.printf("Removed %d of %s. Remaining: %d\n",
                    quantity, name, item.getQuantity());
        }
    }

    @Override // Displays current cart and total
    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
            return;
        }
        System.out.println("\n Cart Items: ");

        // Convert Map values to list to show them in ABC order
        List<CartItem> itemList = new ArrayList<>(cart.values());
        itemList.sort(Comparator.comparing(CartItem::getName));

        // Print header
        System.out.printf("%-18s %-8s %-10s\n", "Item", "Qty", "Price");
        System.out.println("--------------------------------------");

        double total = 0.0;

        // Print each item
        for (CartItem item : itemList) {
            System.out.printf("%-18s x%-7d %10s\n",
                    Utils.capitalize(item.getName()),
                    item.getQuantity(),
                    Utils.format(item.getTotalPrice()));
            total += item.getTotalPrice();
        }

        // Print total (optional)
        System.out.println("--------------------------------------");
        System.out.printf("%-18s %-8s %10s\n", "", "Total:", Utils.format(total));
    }

    @Override // Shows total cost, clears, cart and returns total
    public  double checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart empty, nothing to checkout.");
            return 0.0;
        }
        double total = cart.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
        cart.clear();
        System.out.println("\nCheckout Complete!");
        System.out.println("Total: " + Utils.format(total));
        System.out.println("\nThanks for Shopping!");
        return total;
    }
    // Cart map for testing purposes
    public Map<String, CartItem> getCart() {
        return cart;
    }
}
