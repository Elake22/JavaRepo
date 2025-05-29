import javax.print.DocFlavor;

public class NullEmptyStrings {
    public static void main(String[] args) {

        //Declare string variable set to Null
        String nullString = null;

        // if statement to check if the string is null before printing its length.
        if (nullString == null) {
            System.out.println("The string is null, cannot compute length.");
        } else {
            System.out.println("Length: " + nullString.length());
        }
        // Declare empty string
        String emptyString = "";
        System.out.println("Empty string length: " + emptyString.length());// Prints 0
    }
}

