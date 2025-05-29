
public class ExtractingParts {
    public static void main(String[] args) {
        String sentence = "Learning Java is fun!"; // Declare the string

        // Extract and print

        String firstWord = sentence.substring(0, 8); // Learning positions 1-7, 8 is a space
        String secondWord = sentence.substring(9, 13); // 9-12 is Java
        String lastWord = sentence.substring(17); //Fun!

        // Print
        System.out.println("First word; " + firstWord);
        System.out.println("Second word; " + secondWord);
        System.out.println("Last word; " + lastWord);

    }
}