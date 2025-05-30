import java.util.Scanner;

public class decisions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

// 1. Welcome the player and explain the scenario and print an introduction message
        System.out.println("Welcome to the Treasure Room Game!");
// - Ask if they want to enter the cave
        System.out.println("Do you want to enter the cave? (yes/no)\n>>");
        String caveChoice = scanner.nextLine().toLowerCase();

        if (caveChoice.equals("yes")) {
            System.out.println("You step into the dark cave. There are two paths: left and right. ");
            System.out.println("Which way? (left/right)\n>> ");
            caveChoice = scanner.nextLine().toLowerCase();

            if (caveChoice.equals("left")) {
                System.out.println("You encounter a Emerald Dragon!");
                System.out.print("Do you fight or flee? (fight/flee): ");
                caveChoice = scanner.nextLine().toLowerCase();

                if (caveChoice.equals("fight")) {
                    System.out.println("You slayed the Emerald Dragon and found endless treasure!!");
                } else if (caveChoice.equals("flee")) {
                    System.out.println("You got away safley with you life but no treasure. ");
                } else {
                    System.out.println("Mistake choice. You trip over a rock and the Emerald Dragon attacks!");
                }

            } else if (caveChoice.equals("right")) {
                System.out.println("You discovered the treasure room! ");
                System.out.println("Three items call out to you: a gem, a key and a book");
                System.out.println("Which do you desire most? (gem/key/book): ");
                choice = scanner.nextLine().toLowerCase();

                switch (choice) {
                    case "gem":
                        System.out.println("Your greed knows no end, The gem absorbs you, you are trapped forever");
                        break;
                    case "key":
                        System.out.println("The key unlocks a secret door to another adventure.");
                        break;
                    case "book":
                        System.out.println("The book contains ancient knowledge.");
                        break;
                    default:
                        System.out.println("Item not found. The cave begins to collapse");
                        break;
                }
            } else {
                System.out.println(("Invalid path. You are lost"));
                }
        } else if (caveChoice.equals("no")) {
            System.out.println("You decide to stay safe and avoid the cave. Maybe next time!");
        } else {
            System.out.println("Invalid input. Please restart and try again.");
            }

            System.out.println("\nThanks for playing the Treasure Room Game!");
            scanner.close();
    }

}


// 2. If they enter, present two path choices (left or right)
// - Use an if-else statement to process the decision
// - If they go left, introduce an obstacle or creature
// - If they go right, introduce a treasure room
// 3. If they go left, ask if they want to fight or flee
// - Use a nested if statement to handle the fight scenario
// - If they fight, print a success/failure message
// - If they flee, print a message that they barely escaped
// 4. If they go right, present a switch menu with artifact choices
// - Display three options (e.g., a gem, a key, a book)
// - Use a switch statement to determine the outcome of their choice
// - Each case should have a unique consequence
// 5. Handle invalid inputs with a default response
// - If the user enters an unexpected value, print an error message
// - Optionally, loop back and ask them to re-enter their choice
// 6. End the game with a final message
// - Thank the player for playing
// - Print their fate based on the decisions they made

