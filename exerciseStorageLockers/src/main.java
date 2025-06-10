import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // for user input
        LockerManager manager = new LockerManager(); // manages locker operations
        boolean running = true; // control loop state

        while (running) {
            // Display user options
            System.out.println("\nLocker System Menu:");
            System.out.println("1. Add Locker");
            System.out.println("2. Remove Locker");
            System.out.println("3. Store Item");
            System.out.println("4. Retrieve Item");
            System.out.println("5. Display All Lockers");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();


            switch (choice) {
                case "1": // Add a new locker
                    System.out.print("Enter locker ID to add: ");
                    manager.addLocker(scanner.nextLine());
                    break;
                case "2": // Remove an existing locker
                    System.out.print("Enter locker ID to remove: ");
                    manager.removeLocker(scanner.nextLine());
                    break;
                case "3": // Store an item in a locker
                    System.out.print("Enter locker ID to store item in: ");
                   Locker lockerToStore = manager.getLocker(scanner.nextLine());
                    if (lockerToStore != null) {
                        System.out.print("Enter item: ");
                        lockerToStore.storeItem(scanner.nextLine());
                    } else {
                        System.out.println("Locker not found.");
                    }
                    break;
                case "4": // Retrieve item from locker
                    System.out.print("Enter locker ID to retrieve item from: ");
                    Locker lockerToRetrieve = manager.getLocker(scanner.nextLine());
                    if (lockerToRetrieve != null) {
                        lockerToRetrieve.retrieveItem();
                    } else {
                        System.out.println("Locker not found.");
                    }
                    break;
                case "5": // Display status of all lockers
                    manager.displayAllLockers();
                    break;
                case "6": // Exit the program
                    running = false;
                    System.out.println("Exiting Locker System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close(); // close scanner resource
    }
}