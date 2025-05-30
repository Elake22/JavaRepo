import java.util.Scanner;

public class IntermediateLoops {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // As user for a number
        System.out.print("Enter a number for Multiplication Table\n>>");
        int number = scanner.nextInt();

        // Print table
        System.out.println("Multiplication table for " + number + " : ");
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }

        // Password Validator - While Loop
        System.out.println("------Password Validator - While Loop ------- ");
        scanner.nextLine();
        String password = "";
        while (!password.equals("hello")) {
            System.out.print("Enter the password: ");
            password = scanner.nextLine();
        }
        System.out.println("Access granted");


        // Find the First Vowel - For Loop
        System.out.println("------Find the First Vowel - For Loop ------- ");
        System.out.print("Enter a word to find the first vowel: ");
        String word = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                System.out.println("First vowel '" + ch + "' found at position: " + i);
                found = true;
                break;
            }
        }
        // ATM Do-While-Loop
        System.out.println("---------- ATM Do-While-Loop --------- ");
        double balance = 500.0;
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter withdraw amount: ");
                    double withdraw = scanner.nextDouble();
                    if (withdraw <= balance) {
                        balance -= withdraw;
                        System.out.println("Withdrawal cash below. New balance: $" + balance);
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    balance += deposit;
                    System.out.println("Deposit successful. New balance: $" + balance);
                    break;
                case 3:
                    System.out.println("Current balance: $" + balance);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}






