public class CartDecisions {
    public static void main(String[] args) {
        System.out.println("Welcome to the shopping cart app!");

        java.util.Scanner console = new
                java.util.Scanner(System.in);

        double productPrice = 4.99;
        int productQuantity = 78;
        double totalCost = productPrice * productQuantity;
        System.out.println("Product Price: " +
                productPrice);
        System.out.println("Product Quantity: " +
                productQuantity);
        System.out.println("Total Cost: " + totalCost);


// Prompt for tax exempt
        System.out.println("Are you tax-exempt? (y/n)");
        String taxExempt = console.nextLine();
// Prompt for shipping
        System.out.println("Shipping? (standard / overnight / two-day)");
        String shipping = console.nextLine();
// Prompt for promo code
        System.out.println("Promo code for free shipping?");
        String promoCode = console.nextLine();
// Print details
        System.out.println("\nDetails:");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Promo code: " + promoCode);
        System.out.println("Bye");

        double taxRate = .07;
        double hundredDollarDiscount = .05;
        double fiveHundredDollarDiscount = .10;
        double standardShipping = 2.00;
        double overnightShipping = 10.00;
        double twoDayShipping = 5.00;

        // Apply tax, if necessary
        if (taxExempt.equals("n")) {
            totalCost = totalCost + (totalCost * taxRate);
        }
        System.out.println("Total w/ tax: " + totalCost);
// Apply discount
        if (totalCost >= 500) {
            totalCost = totalCost - (totalCost *
                    fiveHundredDollarDiscount);
        } else if (totalCost >= 100) {
            totalCost = totalCost - (totalCost *
                    hundredDollarDiscount);
        }
        System.out.println("Total after discount: " + totalCost);
        double shippingCost = 0.00;
        switch (shipping) {
            case "standard":
                shippingCost = standardShipping;
                if (promoCode.equals("FREE")) {
                    shippingCost = 0;
                }
                break;
            case "overnight":
                shippingCost = overnightShipping;
                break;
            case "twoday":
                shippingCost = twoDayShipping;
                break;
            default:
// bad shipping type
                System.out.println("Invalid shipping type");
        }
        totalCost += shippingCost;
        System.out.println("Shipping Cost: "+ shippingCost);
        System.out.println("Final Total: " + totalCost);

    }
}