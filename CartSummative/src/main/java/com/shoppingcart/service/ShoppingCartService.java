package com.shoppingcart.service;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.interfaces.CartOperations;
import com.shoppingcart.utility.Utils;

import java.util.HashMap;
import java.util.Map;

// Implements the CartOperations interface
public abstract class ShoppingCartService implements CartOperations {
    // Store items using a map
    private  final Map<String, CartItem> cart = new HashMap<>();

    @Override // Add items to cart or updates if it already exists in cart
    public void addItem(String name, double price, int quantity) {
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
        for (CartItem item : cart.values()) {
            String line = String.format("- %s x%d -> %s", item.getName(),
                    item.getQuantity(), Utils.format(item.getTotalPrice()));
            System.out.println(line);
        }
    }

    @Override // Shows total cost, clears, cart and returns total
    public  double checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart empty, nothing to checkout.");
            return 0.0;
        }
        double total = cart.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
        cart.clear();
        System.out.println("\n Checkout Complete!");
        System.out.println("Total: " + Utils.format(total));
        return total;
    }

}
