import java.util.Scanner;

public class LockersApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LockerManager manager = new LockerManager();
        boolean running = true;

            //Welcome
        System.out.println("Welcome to the Locker System!");

            // Manin menu
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. View Available Lockers");
            System.out.println("2. Rent a Locker");
            System.out.println("3. Access a Locker");
            System.out.println("4. Release a Locker");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            // Switch and cases
            switch (choice) {
                case "1":
                    manager.displayAvailableLockers();
                    break;

                case "2": // Rent locker if available
                    if (manager.hasAvailableLocker()) {
                        int lockerNum = manager.rentLocker();
                        String pin = manager.getLockerPin(lockerNum);
                        System.out.println("Locker " + lockerNum + " rented. PIN: " + pin );
                    } else  {
                        System.out.println("No Locker Available.");
                    }
                    break;

                case "3": // Prompt for locker number and pin access
                    System.out.print("Enter locker number: ");
                    int lockerToAccess = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Pin: ");
                    String accessPin = scanner.nextLine();

                    // Validate Access
                    if (manager.accessLocker(lockerToAccess, accessPin)) {
                        System.out.println("Access Granted!");
                    } else {
                        System.out.println("Access Denied.");
                    }
                    break;

                case "4": // Prompt for locker and Pin to release locker
                    System.out.print("Enter locker number: ");
                    int lockerToRelease = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter PIN: ");
                    String releasePin = scanner.nextLine();

                    // Try to release locker
                    if (manager.releaseLocker(lockerToRelease, releasePin)) {
                        System.out.println("Locker released.");
                    } else {
                        System.out.println("Incorrect PIN or locker number.");
                    }
                    break;

                    // Exit
                case "5":
                    running = false;
                    System.out.println("Thanks you for using Lockers! Goodbye! ");
                    break;

                    // Invalid Menu input
                default:
                    System.out.println("Invalid Selection. Selection option 1-4. ");
            }
        }
        // Close scanner
        scanner.close();
    }
}
