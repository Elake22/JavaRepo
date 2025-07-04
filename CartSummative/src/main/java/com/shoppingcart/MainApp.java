package com.shoppingcart;

import com.shoppingcart.service.ShoppingCartService;
import com.shoppingcart.ui.ConsoleUI;

// Entry Point for App
public class MainApp {

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        ShoppingCartService cartService = new ShoppingCartService();  // Handles cart logic

        boolean running = true;

        ui.showMessage("Welcome to the Shopping Cart!");

        while (running) {
            int choice = ui.getMenuChoice();

            switch (choice) {
                case 1: // Menu option 1 - Display
                    if (cartService.isCartEmpty()) {
                        ui.showMessage("Your cart is empty.");
                    } else {
                        ui.displayCart(cartService.getCartItems());
                    } break;

                case 2: // Menu option 2 - Add Item
                    String name = ui.promptString("Enter item name: ");
                    double price = ui.promptDouble("Enter item price: ");
                    int quantity = ui.promptInt("Enter quantity: ");
                    String addMsg = cartService.addItem(name, price, quantity);
                    ui.showMessage(addMsg);
                    break;

                case 3: // Menu option 3 - Remove Item
                    ui.displayCart(cartService.getCartItems());
                    String removeName = ui.promptString("Enter item name to remove: ");
                    int removeQty = ui.promptInt("Enter quantity to remove: ");
                    String removeMsg = cartService.removeItem(removeName, removeQty);
                    ui.showMessage(removeMsg);
                    break;

                case 4: // Menu option 4 - Checkout
                    String confirm = ui.promptString("Are you sure you want to checkout? (y/n): ");
                    if (confirm.equalsIgnoreCase("y")) {
                        ui.displayCart(cartService.getCartItems()); // Show cart again for review

                        // Ask again to confirm reviewed cart
                        String finalConfirm = ui.promptString("Proceed with final checkout? (y/n): ");
                        if (finalConfirm.equalsIgnoreCase("y")) {
                            double total = cartService.checkout();  // 💬 get final amount
                            ui.showMessage("Checkout complete! Total: " + String.format("$%.2f", total));

                        } else {
                            ui.showMessage("Final checkout cancelled. Returning to menu.");
                        }
                    } else {
                        ui.showMessage("Checkout cancelled. Returning to menu.");
                    }
                    break;

                case 5: // Menu option 5 - Exit
                    running = false;
                    ui.showMessage("Thank you for using Shopping Cart!");
                    break;

                default: // Invalid input
                    ui.showMessage("Invalid option. Please try again.");
                    break;
            }
        }
    }
}

