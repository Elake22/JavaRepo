import java.util.Scanner;

public class IntermediateLoops {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // As user for a number
        System.out.print("Enter a number for Multiplication Table\n>>");
        int number = scanner.nextInt();

        // Print table
        System.out.println("Mutiplication table for " + number + " : ");
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

        scanner.close();

    }
}

