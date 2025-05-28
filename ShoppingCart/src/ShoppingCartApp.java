// Shopping Cart

public class ShoppingCartApp {

    // ORDER STATUS
    enum OrderStatus{
        PENDING, PROCESSING, SHIPPED, DELIVERED
    }
    // METHOD
    enum ShippingStatus{
        STANDARD, TWO_DAY, OVERNIGHT
    }

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


        System.out.println(ShippingStatus.STANDARD);
        System.out.println(ShippingStatus.TWO_DAY);
        System.out.println(ShippingStatus.OVERNIGHT);
        System.out.println(OrderStatus.PENDING);
        System.out.println(OrderStatus.PROCESSING);
        System.out.println(OrderStatus.SHIPPED);
        System.out.println(OrderStatus.DELIVERED);
       
        System.out.println("Bye");

    }
}