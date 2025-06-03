public class LessonDemo {
    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        String[] inputs = {"red", "yellow", "green"};
        String[] outputs = {"stop", "slow down", "continue"};
        for (int i = 0; i < inputs.length; i++) {
            String input = inputs[i];
            String output = outputs[i];
            System.out.print("Testing with " + input + "... ");
            String actual = reactToLight(input);
            if (output.equals(actual)) {
                System.out.println("Passed!");
            } else {
                System.out.println("FAIL: Expected '" + output
                        + "', got '" + actual + "'");
            }
        }
    }
}
