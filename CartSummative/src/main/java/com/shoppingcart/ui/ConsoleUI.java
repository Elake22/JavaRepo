package com.shoppingcart.ui;

import java.util.Scanner;

// Handles input and output of cart App
public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);

    // Menu display
    public int getMenuChoice() {
        System.out.println("\n---- Shopping Cart Menu ----");
        System.out.println("1. Display Cart");
        System.out.println("2. Add Item");
        System.out.println("3. Remove Item");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
        System.out.print("Choose an option (1-5): ");

        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    // String input (Item name)
    public String promptString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
    // Integer input (Item quantity)
    public int promptInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
    // Double input (Item price)
    public double promptDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
    //Help display messages from main app
    public void showMessage(String message) {
        System.out.println(message);
    }
}



