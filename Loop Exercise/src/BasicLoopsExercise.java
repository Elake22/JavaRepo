import java.util.Random;
import java.util.Scanner;

public class BasicLoopsExercise {
    public static void main(String[] args) {

        // Count Up - For Loop
        System.out.println("------Count Up - Even Numbers from 1 to 100---------");
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        } // Count Down While Loop
        System.out.println("\n\n-------Countdown Timer - While Loop---------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a starting number for countdown: ");
        int count = scanner.nextInt();

        while (count >= 0) {
            System.out.println(count);
            count--;
        }
        System.out.println("Blast off!");

     // Guess the Number Do-While-Loop
        System.out.println("\n\n-------Guess the Number-Do-While-Loop---------");
        System.out.println("\nGuess the Number Game:");
        Random random = new Random();
        int targetNumber = random.nextInt(3) + 1;
        int guess;

        do {
        System.out.print("Guess a number between 1 and 50: ");
        guess = scanner.nextInt();

        if (guess != targetNumber) {
            System.out.println("Wrong guess. Try again.");
        }
    }   while (guess != targetNumber);

        System.out.println("Congratulations! You guessed the number: " + targetNumber);
        scanner.close();


    }
}