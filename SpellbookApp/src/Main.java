import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SpellBook spellBook = new SpellBook();
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("---Welcome to the Wizard's SpellBook!---");
        System.out.println("Type 'help' to see available spells. Type 'quit' to exit.");

        // Spell casting loop to cast until quit
        while (true) {
            System.out.print("\nRecite a spell: ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("help")) {
                spellBook.getHelp(); // Shows spell list

            } else {
                spellBook.tryIncantation(input); // Cast spell
            }

        }
    }

}

