import java.util.Scanner;
public class LockersApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LockerService service = new LockerService(new LockerManager());
        boolean exit = false;

            //Welcome
        System.out.println("Welcome to the Locker System!");

            // Main menu
        while (!exit) {
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
                case "1": // Show Open lockers
                    System.out.print(service.availableLockersMessage());
                    break;

                case "2": // Rent locker if available
                    Result rentResult = service.rentLocker();
                    System.out.println(rentResult.getMessage());
                    break;

                case "3": // Prompt for locker number and pin access
                    Integer lockerToAccess = IOUtilities.getLockerNumber(scanner);
                    if (lockerToAccess == null) {
                        System.out.println("Invalid locker number.");
                        break;
                    } // Validate PIN access
                    String accessPin = IOUtilities.getLockerPin(scanner);
                    Result accessResult = service.accessLocker(lockerToAccess, accessPin);
                    System.out.println(accessResult.getMessage());
                    break;

                case "4": // Prompt for locker and PIN to release locker
                    Integer lockerToRelease = IOUtilities.getLockerNumber(scanner);
                    String releasePin = IOUtilities.getLockerPin(scanner);
                    String confirm = IOUtilities.getConfirmation(scanner);

                    Result releaseResult = service.handleLockerRelease(lockerToRelease, releasePin, confirm);
                    System.out.println(releaseResult.getMessage());
                    break;

                case "5": // Exit
                    System.out.println("Thank you for using Lockers! Goodbye! ");
                    exit = true;
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
