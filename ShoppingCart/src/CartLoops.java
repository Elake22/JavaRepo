import java.util.Scanner;

public class CartLoops {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean orderConfirmed = false;

        while (!orderConfirmed) {
            System.out.println("Welcome to the shopping cart app!");

            // Ask if tax-exempt
            System.out.print("Are you tax-exempt? (y/n)\n");
            String taxExempt = scanner.nextLine();

            // Ask for shipping method
            System.out.print("Shipping? (standard/overnight/twoday)\n");
            String shippingMethod = scanner.nextLine();

            // Ask for order quantity
            System.out.print("Order quantity?\n");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Ask for promo code
            System.out.print("Promo code for free shipping?\n");
            String promoCode = scanner.nextLine();

            // Confirm order
            System.out.print("Confirm Order y/n\n");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                orderConfirmed = true;
            }
        }
    }
}
