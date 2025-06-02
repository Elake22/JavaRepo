// Shopping Cart
import java.util.Scanner;

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
        Scanner console = new Scanner(System.in);

        System.out.println("Shopping Cart");

        // 1. Product Info
        int productID = 1;
        int productCategory =2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        System.out.println("---------Cost and Profit--------");
        // 2. Total cost based on Inventory
        double totalCost = productCost * productQuantity;
        System.out.println("Total Cost: $" + totalCost );

        // 3. Profit Margin Price - cost
        double productProfit = productPrice - productCost;
        System.out.println("Profit  Margin: $" + productProfit);

        // 4. Profit Potential   product x Quantity
        double totalProfit = productProfit * productQuantity;
        System.out.println("Total Possible Profit: $" + totalProfit);

        System.out.println("---------Shipping & Status--------");
        System.out.println(ShippingStatus.STANDARD);
        System.out.println(ShippingStatus.TWO_DAY);
        System.out.println(ShippingStatus.OVERNIGHT);
        System.out.println(OrderStatus.PENDING);
        System.out.println(OrderStatus.PROCESSING);
        System.out.println(OrderStatus.SHIPPED);
        System.out.println(OrderStatus.DELIVERED);
        System.out.println("Bye");

        // Strings Code-Along
        String businessName = "My Business";
        String businessContactInfo = "123-456-7890";
        String productDescription = "My product description";

        System.out.println("---------Business--------");
        System.out.println("Business name: " + businessName);
        System.out.println("Business contact info: " + businessContactInfo);
        System.out.println("Product description: " + productDescription);


        //------Console I/O Section_______
        // Prompt for tax exempt
        System.out.println("---------Tax Exemption, Shipping, Promo Code--------");
        System.out.println("Are you tax-exempt? (y/n)");
        String taxExempt = console.nextLine();

        // Prompt for shipping
        System.out.print("Shipping? (y/n) ");
        String shipping = console.nextLine();

        // Prompt for order quantity
        System.out.print("Order quantity? ");
        int orderQuantity = Integer.parseInt(console.nextLine());

        // Prompt for promo code
        System.out.print("Promo code for free shipping? ");
        String promoCode = console.nextLine();

        // IO prints
        System.out.println("\nDetails:");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity: " + orderQuantity);
        System.out.println("Promo code: " + promoCode);

    }
}