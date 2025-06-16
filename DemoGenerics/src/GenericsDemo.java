import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GenericsDemo {
    public static void main(String[] args) {
        // Creating a Container specifically for Integers
        Container<Integer> wholeNumberContainer = new Container<>();
        wholeNumberContainer.set(42); // Placing the number 42 inside our box

        // Opening the box and printing what's inside
        System.out.println("Stored in container: " + wholeNumberContainer.get());

        // Creating an ArrayList to store customer orders
        ArrayList<String> orders = new ArrayList<>();
        orders.add("Order #1001");
        orders.add("Order #1002");
        orders.add("Order #1003");

        // Displaying all orders in the list
        System.out.println("\nList of Orders:");
        for (String order : orders) {
            System.out.println(order);
        }

        // Creating a Queue to process customer orders in FIFO order
        Queue<String> orderQueue = new LinkedList<>();
        orderQueue.add("Customer 1 - Limited Edition Poster");
        orderQueue.add("Customer 2 - Limited Edition Poster");
        orderQueue.add("Customer 3 - Limited Edition Poster");

        // Processing orders in the order they arrived
        System.out.println("\nProcessing Orders:");
        while (!orderQueue.isEmpty()) {
            System.out.println("Processing: " + orderQueue.poll());
        }
    }
}