// Prime Word Counter
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Word counter
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        String[] words = sentence.trim().split("\\s+");
        int wordCount = words.length;

        System.out.print("Word Count: " + wordCount);
        scanner.close();
    }
}

