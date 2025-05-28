// Shopping Cart

public class Main {
    public static void main(String[] args) {

        System.out.printf("Shopping Cart");

        // 1. Product Info
        int productID = 1;
        int productCategory =2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        // 2. Total cost based on Inventory
        double totalCost = productCost * productQuantity;
        System.out.println(" Total Cost: $" + totalCost );

        // 3. Profit Margin Price - cost
        double productProfit = productPrice - productCost;
        System.out.println("Profit  Margin: $" + productProfit);

        // 4. Profit Potential   product x Quantity
        double totalProfit = productProfit * productQuantity;
        System.out.println("Total Possible Profit: $" + totalProfit);


    }
}