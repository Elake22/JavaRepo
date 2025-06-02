import java.util.Scanner;
public class Part3Arrays {
    public static void main(String[] args) {

        // 7. Create an array of 5 test scores
        int[] scores = {85, 92, 78, 90, 88};

        // Calculate the sum of all scores
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        System.out.println("Total Score: " + sum);

        // 8. Find the Maximum & Minimum
        int max = scores[0];
        int min = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }
        System.out.println("Highest Score: " + max);
        System.out.println("Lowest Score: " + min);

        // 9. Calculate the Average
        double average = (double) sum / scores.length;
        System.out.println("Average Score: " + average);
    }
}