import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // 2: Use the Generic Box
        Box<Integer> numberBox = new Box<>("Number Box", 42);
        Box<String> messageBox = new Box<>("Message Box", "Hello, Generics!");

        numberBox.displayContents();
        messageBox.displayContents();

        // 3: Store Items in a List
        ArrayList<Box<Integer>> numberBoxes = new ArrayList<>();
        numberBoxes.add(new Box<>("Box 1", 10));
        numberBoxes.add(new Box<>("Box 2", 20));
        numberBoxes.add(new Box<>("Box 3", 30));

        System.out.println("\nArrayList of Boxes:");
        for (Box<Integer> box : numberBoxes) {
            box.displayContents();
        }

        // 4: Process Items in a Queue
        Queue<Box<String>> messageQueue = new LinkedList<>();
        messageQueue.add(new Box<>("Queue Box 1", "First in Line"));
        messageQueue.add(new Box<>("Queue Box 2", "Second in Line"));
        messageQueue.add(new Box<>("Queue Box 3", "Third in Line"));

        System.out.println("\nProcessing Queue:");
        while (!messageQueue.isEmpty()) {
            Box<String> box = messageQueue.poll();
            box.displayContents();
        }
    }
}