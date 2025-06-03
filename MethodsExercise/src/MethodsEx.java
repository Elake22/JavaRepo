public class MethodsEx {
    public static void main(String[] args) {
        // Task 1
        printWelcomeMessage();

        // Task 2
        int sumResult = sum(7, 3);
        System.out.println("Sum: " + sumResult);

        // Task 3
        convertToFahrenheit(35.0);

        // Task 4
        isEven(6);
        isEven(9);

        // Task 5
        printMultipleTimes("Java", 3);

        // Task 6
        findMax(12, 45, 33);

        // Task 7
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Factorial of 7: " + factorial(7));
        System.out.println("Factorial of 10: " + factorial(10));

        // Task 8
        greet("Elijah");
        greet("John", 34);

        // Task 9
        System.out.println("Vowels in 'hello world': " + countVowels("hello world"));
        System.out.println("Vowels in 'java programming': " + countVowels("java programming"));

        // Task 10
        System.out.println("Calc 2 + 2: " + calculator(2, 2, '+'));
        System.out.println("Calc 10 - 4: " + calculator(10, 4, '-'));
        System.out.println("Calc 6 * 5: " + calculator(6, 5, '*'));
        System.out.println("Calc 20 / 4: " + calculator(20, 4, '/'));
        System.out.println("Calc 10 / 0: " + calculator(10, 0, '/'));

    }

    // Task 1: Simple Welcome Message
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the Java Methods Exercise!");
    }

    // Task 2: Sum of Two Numbers
    public static int sum(int a, int b) {
        return a + b;
    }

    // Task 3: Convert Celsius to Fahrenheit
    public static double convertToFahrenheit(double celsius) {
        double fahrenheit = (celsius * 9 / 5) + 32;
        System.out.println(celsius + " Celsius is " + fahrenheit + " Fahrenheit.");
        return fahrenheit;
    }

    // Task 4: Check If a Number is Even or Odd
    public static boolean isEven(int number) {
        boolean result = number % 2 == 0;
        if (result) {
            System.out.println(number + " is even");
        } else {
            System.out.println(number + " is odd");
        }
        return result;
    }

    // Task 5: Print a String Multiple Times
    public static void printMultipleTimes(String word, int times) {
        System.out.println("Repeating the word: ");
        for (int i = 0; i < times; i++) {
            System.out.println(word);
        }
    }

    // Task 6: Find the Maximum of Three Numbers
    public static int findMax(int a, int b, int c) {
        int max;
        if (a >= b && a >= c) {
            max = a;
        } else if (b >= a && b >= c) {
            max = b;
        } else {
            max = c;
        }
        System.out.println("The maximum of (" + a + ", " + b + ", " + c + ") is: " + max);
        return max;
    }

    // Task 7: Factorial Using Recursion
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1; // Base case
        } else {
            return n * factorial(n - 1); // Recursive case
        }
    }
    // 8: Method Overloading (Greeting Message)
    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static void greet(String name, int age) {
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
    }
    // 9: Count Vowels in a String
    public static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }
    // Task 10: Mini Calculator
    public static double calculator(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return (double) num1 / num2;
                } else {
                    System.out.println("Error: Division by zero");
                    return Double.NaN;
                }
            default:
                System.out.println("Error: Invalid operator");
                return Double.NaN;
        }
    }
}
