import java.util.Scanner;

public class LockersApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LockerManager manager = new LockerManager();
        boolean running = true;

        System.out.println("Welcome to the Locker System!");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Rent a Locker");
            System.out.println("2. Access a Locker");
            System.out.println("3. Release a Locker");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
        }
    }
}
