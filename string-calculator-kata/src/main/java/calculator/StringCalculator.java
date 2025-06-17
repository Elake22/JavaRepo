package calculator;

import java.util.regex.*;
import java.util.*;

public class StringCalculator {


    public int add(String input) {
        // Return 0 for empty or null input
        if (input == null || input.isEmpty()) return 0;

        String numberSection = input; // Part of the string that contains the numbers
        String delimiterPattern = "[,\n]"; // Default delimiters: comma or newline

        // Check for custom delimiter definition at the start of the input
        if (input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");

            // Extract the custom delimiter definition section (e.g., "//[***]\n")
            String delimiterDefinition = input.substring(2, delimiterEndIndex);

            // Extract the actual numbers portion after the delimiter line
            numberSection = input.substring(delimiterEndIndex + 1);

            // Generate a regex pattern to split the numbers using one or more delimiters
            delimiterPattern = buildDelimiterRegex(delimiterDefinition);
        }

        // Split the numbers using the delimiter pattern and compute the total
        return calculateSum(numberSection, delimiterPattern);
    }

    // Builds a regex pattern to Split wherever there’s a comma or a newline.
    //Supports multi-character and multiple delimiters in the format: //[delim1][delim2]

    private String buildDelimiterRegex(String definition) {
        List<String> delimiters = new ArrayList<>();

        // Regex to match any substring enclosed in square brackets (e.g., [***], [%])
        Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(definition);

        // Extract all bracketed delimiters and escape them for regex use
        while (matcher.find()) {
            delimiters.add(Pattern.quote(matcher.group(1)));
        }

        // If no brackets were found, it's a single character delimiter like ";"
        if (delimiters.isEmpty()) {
            delimiters.add(Pattern.quote(definition));
        }

        // Join all delimiters using "|" to create a regex OR pattern
        return String.join("|", delimiters);
    }
    //Splits the number section using the given delimiter pattern,
    private int calculateSum(String numbers, String delimiterRegex) {
        String[] parts = numbers.split(delimiterRegex);
        List<Integer> negatives = new ArrayList<>();
        int sum = 0;

        for (String part : parts) {
            if (part.isBlank()) continue;

            int number = Integer.parseInt(part.trim());

            // Collect negatives for error reporting
            if (number < 0) {
                negatives.add(number);
            }
            // Ignore numbers greater than 1000
            else if (number <= 1000) {
                sum += number;
            }
        }

        // Throw exception if any negative numbers were found and replace it with ""
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " +
                    negatives.toString().replaceAll("[\\[\\]]", ""));
        }
        return sum;
    }
}
