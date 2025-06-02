import java.util.Random;
import java.util.HashSet;

public class AdvancedChallenges {
    public static void main(String[] args) {

        // 10. Count Occurrences of a Value
        int[] numbers = new int[10];
        Random rand = new Random();
        int count3 = 0;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(5) + 1; // Random numbers between 1 and 5
            if (numbers[i] == 3) {
                count3++;

            }
        }
        System.out.println("Count of number 3: " + count3);

        // 11. Shift Elements in an Array
        int[] shiftArray = {1, 2, 3, 4, 5};
        int firstElement = shiftArray[0];

        for (int i = 0; i < shiftArray.length - 1; i++) {  // Shift to last position
            shiftArray[i] = shiftArray[i + 1];
        }
        shiftArray[shiftArray.length - 1] = firstElement;

        System.out.print("Shifted Array: ");
        for (int num : shiftArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 12. Check for Duplicates
        String[] studentNames = {"John", "Bob", "Steve", "Bob", "Roy", "Sam"};
        HashSet<String> seenNames = new HashSet<>();
        boolean hasDuplicates = false;

        for (String name : studentNames) {
            if (seenNames.contains(name)) {
                hasDuplicates = true;
                break;
            }
            seenNames.add(name);
        }

        if (hasDuplicates) {
            System.out.println("Duplicates found!");
        } else {
            System.out.println("No duplicates found.");
        }
    }
}