public class SplittingStrings {
    public static void main(String[] args) {

        // Declare String
        String csvData = "apple,banana,grape,orange";
        String [] fruits = csvData.split(",");

        // Loop
        for (int i = 0; i < fruits.length; i++) {
            System.out.println("Fruit " + (i + 1) + ": " + fruits [i]);

        }

    }
}
