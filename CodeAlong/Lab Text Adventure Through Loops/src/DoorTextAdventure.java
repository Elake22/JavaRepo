import java.util.Scanner;

public class DoorTextAdventure {
    public static void main(String[] args) {


        // Welcome message and setup
        System.out.println("Welcome to the Text Adventure Game!\n");
        Scanner console = new Scanner(System.in);
        boolean keepRunning = true;
        String[] rooms = new String[5];
        String[] unlockedRooms = new String[5];
        boolean[] keys = new boolean[4];

        // Game menu and room descriptions
        String mainMenu = "You find yourself in a room with 5 doors. Which one do you enter?\n" +
                "1. The unpainted wooden door\n" +
                "2. The black metal door\n" +
                "3. The blue painted wooden door\n" +
                "4. The yellow painted wooden door\n" +
                "5. The green painted wooden door\n" +
                "Door Selection: ";

        rooms[0] = "You've entered a room lit with candles. You see a desk with half a key!";
        rooms[1] = "You've entered a room cold and wet. Upon further inspection, you see a hole in the far wall. Something sparkles and catches your attention. It is half of a key!";
        rooms[2] = "You've entered a room the air is dry and warm. There is a rug that looks recently moved. When you pull the rug back, it reveals a key!";
        rooms[3] = "You've entered a room with a set of armor. Around its neck holds a key!";
        rooms[4] = "You've found a room with a red door... it's locked... do you have a key?";

        unlockedRooms[0] = "You've entered a room lit with candles. You've been here before. ";
        unlockedRooms[1] = "You've entered a room cold and wet. You've been here before. ";
        unlockedRooms[2] = "You've entered a room the air is dry and warm. You've been here before. ";
        unlockedRooms[3] = "You've entered a room with a set of armor. You've been here before. ";
        unlockedRooms[4] = "You've found a room with a red door... you've used the keys you have found and open the door!";



        // Get player name
        String name = promptString("Hello, Please enter your name: ");
        print("Your name is: " + name);

        // Main game loop

            while (keepRunning) {
                try {
                String direction = promptString(mainMenu);
                int door = Integer.parseInt(direction);
                switch (door) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        findKeyAndDisplayRoomText(rooms, unlockedRooms, keys,
                                door - 1);
                        break;
                    case 5:
                        boolean unlock = true;
                        for (int i = 0; i < keys.length; i++) {
                            if (keys[i] == false) {
                                System.out.println(rooms[4]);
                                unlock = false;
                                break;
                            }
                        }
                        if (unlock) {
                            System.out.println(unlockedRooms[4]);
                            keepRunning = false;
                        }
                        break;

                    default: // Invalid input
                        System.out.println("Unable to find the door you are looking for");
                }
            } catch(NumberFormatException e){
                print("Invalid menu option. Please try again.");
            }
        }
        // Farewell message
        System.out.println("\nBye, " + name + "!");
    }
    public static void print(String message) {
        System.out.println(message);
    }
    public static String promptString(String message) {
        Scanner console = new Scanner(System.in);
        print(message);
        return console.nextLine();
    }
    public static void findKeyAndDisplayRoomText(String[]
    rooms, String[] unlockedRooms, boolean[] keys, int door) {
        if (keys[door] == true) {
            print(unlockedRooms[door]);
        }else{
            print(rooms[door]);
            keys[door] = true;
        }
    }
}
