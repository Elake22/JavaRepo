public class ReplacingCharacters{
    public static void main(String[] args) {

        // Declare string
        String sentence = "The quick brown fox.";

        // Replace
        String modifiedSentence = sentence.replace("quick", "slow").replace(" ", "_");

        // Print
        System.out.println("Modified Sentence: " + modifiedSentence);

    }
}
