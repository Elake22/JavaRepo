package org.example.shoppingcart;

/**
 * Create enums for order status and shipping status.
 * Here are the options as per decisions made by the team:
 * 1. Order status: pending, processing, shipped, delivered
 * 2. Shipping status: standard, 2-day, overnight
 */

public class ShoppingCartApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");
        // Product:
        // Product ID is 1
        // Product Category is 2
        // Product Cost is 2.56
        // Product Price is 4.99
        // Product Quantity is 78

        // 1. Assign variables to each of the elements above.
        int productId = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        // 2. Write code to calculate the total cost of the product based on the
        // inventory.
        double totalCost = productCost * productQuantity;
        System.out.println(totalCost);

        // 3. Write code to calculate the profit margin of the product.
        double profitMargin = productPrice - productCost;
        System.out.println(profitMargin);

        // 4. Write code to calculate the total potential profit.
        // total profit? profitMargin * productQuantity
        double totalProfit = profitMargin * productQuantity;
        System.out.println(totalProfit);

        System.out.println("Bye");
    }
}
