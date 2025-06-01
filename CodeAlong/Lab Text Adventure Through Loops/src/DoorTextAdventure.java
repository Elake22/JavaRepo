import java.util.Scanner;

public class DoorTextAdventure {
    public static void main(String[] args) {

        // Welcome message and setup
        System.out.println("Welcome to the Text Adventure Game!\n");
        Scanner console = new Scanner(System.in);
        boolean keepRunning = true; // Controls game loop
        boolean key1 = false; // Tracks if player found first key half
        boolean key2 = false; // Tracks if player found second key half

        // Game menu and room descriptions
        String mainMenu = "\nYou find yourself in a room with 3 doors. Which one do you enter?"
                + "\n1. The unpainted wooden door"
                + "\n2. The black metal door"
                + "\n3. The blue painted wooden door"
                + "\nDoor Selection: ";

        String room1 = "You've entered a room lit with candles. You see a desk with find half of a key!";
        String room1_visited = "You've entered a room lit with candles. You've been here before. There is an empty desk.";

        String room2 = "You've entered a room cold and wet. Upon further inspection, you see a hole in the far wall. Something sparkles and catches your attention. It is half of a key!";
        String room2_visited = "You've entered a room cold and wet. You've been here before.";

        String room3_locked = "You've found a room with a red door... it's locked... do you have a key?";
        String room3_unlocked = "You've found a room with a red door... you've used the key you have found and opened the door!";

        // Get player name
        System.out.print("Hello, Please enter your name: ");
        String name = console.nextLine();
        System.out.println("Your name is: " + name);

        // Main game loop
        while (keepRunning) {
            System.out.print(mainMenu);
            String direction = console.nextLine();

            switch (direction) {
                case "1": // Wooden door
                    if (key1) {
                        System.out.println(room1_visited);
                    } else {
                        key1 = true;
                        System.out.println(room1);
                    }
                    break;

                case "2": // Metal door
                    if (key2) {
                        System.out.println(room2_visited);
                    } else {
                        key2 = true;
                        System.out.println(room2);
                    }
                    break;

                case "3": // Blue door leading to red door
                    if (key1 && key2) {
                        System.out.println(room3_unlocked);
                        keepRunning = false; // Exit loop
                    } else {
                        System.out.println(room3_locked);
                    }
                    break;

                default: // Invalid input
                    System.out.println("Unable to find the door you are looking for");
            }
        }

        // Farewell message
        System.out.println("\nBye, " + name + "!");
    }
}
