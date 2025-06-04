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
                    System.out.print(service.availableLockersMessage());
                    break;

                case "2": // Rent locker if available
                    Result rentResult = service.rentLocker();
                    System.out.println(rentResult.getMessage());
                    break;

                case "3": // Prompt for locker number and pin access
                    System.out.print("Enter locker number: ");
                    String accessStr = scanner.nextLine();
                    Integer lockerToAccess = IOUtilities.parseIntSafe(accessStr);
                    if (lockerToAccess == null) {
                        System.out.println("Invalid locker number.");
                        break;
                    }
                    System.out.print("Enter Pin: ");
                    String accessPin = scanner.nextLine();

                    // Validate Access
                    Result accessResult = service.accessLocker(lockerToAccess, accessPin);
                    System.out.println(accessResult.getMessage());
                    break;

                case "4": // Prompt for locker and PIN to release locker
                    System.out.print("Enter locker number: ");
                    String releaseStr = scanner.nextLine();
                    Integer lockerToRelease = IOUtilities.parseIntSafe(releaseStr);
                    if (lockerToRelease == null) {
                        System.out.println("Invalid locker number.");
                        break;
                    }
                    System.out.print("Enter PIN: ");
                    String releasePin = scanner.nextLine();

                // Try to release locker
                Result releaseResult = service.releaseLocker(lockerToRelease, releasePin);
                System.out.println(releaseResult.getMessage());
                break;

                    // Exit
                case "5":
                    running = false;
                    System.out.println("Thank you for using Lockers! Goodbye! ");
                    break;

                    // Invalid Menu input
                default:
                    System.out.println("Invalid Selection. Choose option 1-5. ");
            }
        }
        // Close scanner
        scanner.close();
    }
}
