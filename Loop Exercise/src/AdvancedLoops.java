import java.util.Scanner;

public class AdvancedLoops {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Fizz Buzz
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
        // Reverse a String
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter a word to reverse\n>>");
        String word = scanner1.nextLine();

        System.out.print("Reversed word: ");
        for (int i = word.length() - 1; i >= 0; i--) {
            System.out.print(word.charAt(i));
        }
        System.out.println();

    }
}

