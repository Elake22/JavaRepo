import java.util.Scanner;

public class CartArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the shopping cart app!");

        // Arrays for addresses and sizes
        String[] shippingAddresses = {"123 Main St", "456 Main St", "789 Main St"};
        String[] productSizes = {"small", "medium", "large"};

        // Display address options
        System.out.println("Shipping Addresses:");
        for (int i = 0; i < shippingAddresses.length; i++) {
            System.out.println((i + 1) + ". " + shippingAddresses[i]);
        }

        // Ask user to choose a shipping address
        System.out.print("Shipping address? ");
        int shippingChoice = scanner.nextInt();
        String chosenAddress = shippingAddresses[shippingChoice - 1];

        // Enter quantity
        System.out.print("Order quantity? ");
        int quantity = scanner.nextInt();

        // Display product size options
        System.out.println("Available Sizes:");
        for (int i = 0; i < productSizes.length; i++) {
            System.out.println((i + 1) + ". " + productSizes[i]);
        }

        // Choose a size
        System.out.print("Size? ");
        int sizeChoice = scanner.nextInt();
        String chosenSize = productSizes[sizeChoice - 1];

        // Check for promo code
        scanner.nextLine(); // clear input buffer
        System.out.print("Promo code for free shipping? ");
        String promo = scanner.nextLine();
        boolean freeShipping = promo.equalsIgnoreCase("FREESHIP");

        // Print order summary
        System.out.println("\nOrder Summary:");
        System.out.println("Address: " + chosenAddress);
        System.out.println("Quantity: " + quantity);
        System.out.println("Size: " + chosenSize);
        System.out.println("Free Shipping: " + (freeShipping ? "Yes" : "No"));

        scanner.close();

    }
}
