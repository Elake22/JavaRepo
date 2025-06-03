import java.util.Scanner;

public class ExceptionHandling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = 0;
        boolean validAge = false;

        // Validate age input
        while (!validAge) {
            try {
                System.out.print("Enter your age");
                age = Integer.parseInt(scanner.nextLine());// parse into integer

                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive number.");
                }
                validAge = true;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid age.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        // Prompt for email and check for null
        String email = "";
        boolean validEmail = false;
        while (!validEmail) {
            try {
                System.out.print("Enter your email: ");
                email = scanner.nextLine();
                if (email == null) {
                    throw new NullPointerException("Email input was null.");
                }
                if (email.trim().isEmpty()) {
                    throw new IllegalArgumentException("Email cannot be empty. Please enter a valid email.");
                }
                if (!email.contains("@") || !(email.endsWith(".com") || email.endsWith(".net"))) {
                    throw new IllegalArgumentException("Email must contain '@' and end with '.com' or '.net'.");
                }
                validEmail = true;
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Prompt for pin and Exceptions
        int pin = 0;
        boolean validPin = false;
        while (!validPin) {
            try {
                System.out.print("Enter your 4-digit PIN: ");
                String pinInput = scanner.nextLine();
                if (pinInput == null) {
                    throw new NullPointerException("PIN input was null.");
                }
                pin = Integer.parseInt(pinInput);

                if (String.valueOf(pin).length() != 4) {
                    throw new IllegalArgumentException("PIN must be 4 digits.");
                }
                validPin = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for the PIN.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 5. Use a finally block to indicate that the registration attempt is complete
        try {
            System.out.println("\nRegistration Successful!");
            System.out.println("Age: " + age);
            System.out.println("Email: " + email);
            System.out.println("PIN: " + pin);
        } finally {
            System.out.println("Registration attempt is complete.");
            scanner.close();
        }
    }
}
