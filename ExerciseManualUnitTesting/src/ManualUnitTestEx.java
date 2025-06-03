public class ManualUnitTestEx {

    public static void main(String[] args) {
        runTest();

    }
    // Task 1: Happy Path Testing
    public static String selectDrink(String choice) {
        if (choice == null) {
            System.out.println("Invalid selection: null input");
            return "Invalid selection";
        }

        choice = choice.toLowerCase();
        System.out.println("Lowercased input: " + choice );

        if (choice.equals("water")) {
            return "You selected Water";
        } else if (choice.equals("soda")) {
            return "You selected Soda";
        } else if (choice.equals("juice")) {
            return "You selected Juice";
        } else if (choice.equals("WATER")) {
            return "You selected WATER";
        }else if (choice.equals("Tea")) {
            return "You selected Tea";
        }else if (choice.equals(" ")) {
            return "You selected _";
        } else {
            return "Invalid selection";
        }
    }

    // Task 1: Method
    private static void runTest() {
        System.out.println("Testing with water... " +
                (selectDrink("water").equals("You selected Water") ? "Passed!" : "Failed"));

        System.out.println("Testing with soda... " +
                (selectDrink("soda").equals("You selected Soda") ? "Passed!" : "Failed"));

        System.out.println("Testing with juice... " +
                (selectDrink("juice").equals("You selected Juice") ? "Passed!" : "Failed"));

        System.out.println("Testing with WATER... " +
                (selectDrink("WATER").equals("You selected WATER") ? "Passed!" : "FAILED: 'You selected Water', got '" + selectDrink("WATER") + "'"));

        System.out.println("Testing with Tea... " +
                (selectDrink("Tea").equals("Invalid selection") ? "Passed!" : "Failed"));

        System.out.println("Testing with empty string... " +
                (selectDrink("").equals("Invalid selection") ? "Passed!" : "Failed"));

        System.out.println("Testing with null... " +
                (selectDrink(null).equals("Invalid selection") ? "Passed!" : "Failed"));
    }
}



