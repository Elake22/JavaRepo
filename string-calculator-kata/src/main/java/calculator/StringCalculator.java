package calculator;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    // Method to add numbers from a comma-separated string (or custom delimiter)
    public int add(String numbers) {

        // Step 1: Handle null or empty input
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = "[,\n]";  // Default: comma or newline
        String numSection = numbers;

        // Step 2: Handle custom delimiter syntax, e.g., "//;\n1;2"
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n"); // Find the newline after delimiter
            String customDelimiter = numbers.substring(2, delimiterIndex); // Extract delimiter

            // Handle muli-character delimiters
            if(customDelimiter.startsWith("[") && customDelimiter.endsWith("]")) {

            }
        }

        // Step 3: Split numbers using the appropriate delimiter
        String[] parts = numSection.split(delimiter);
        int sum = 0;

        List<Integer> negatives = new ArrayList<>();

        // Step 4: Loop through each number, trim and add
        for (String part : parts) {
            part = part.trim();
            if (!part.isEmpty()) {
                int number = Integer.parseInt(part);

                if (number < 0) {
                    negatives.add(number); // collect negatives
                } else if (number <= 1000) { // Only add if <= 1000
                    sum += number;
                }
            }
        }

        // After loop: check and throw if any negatives found
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " +
                    negatives.toString().replaceAll("[\\[\\]]", ""));
        }
    return sum;
    }
}
