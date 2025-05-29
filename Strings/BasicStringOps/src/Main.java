public class Main {
    public static void main(String[] args) {
        // Declare and assign
        String firstName = "Harry";
        String lastName = "Potter";
        String fullName = firstName + "" + lastName;

        // Print full name
        System.out.println("Full name: " + fullName);

        // Find the length of fullName and print
        int length = fullName.length();
        System.out.println("Length: " + length);

        // Extract and print charAt(0)
        char firstChar = fullName.charAt(0);
        System.out.println("First character; " + firstChar);

        // Find position of letter r
        int indexOfR = fullName.indexOf('r');
        System.out.println("Index of 'r': " + indexOfR);
        

    }
}