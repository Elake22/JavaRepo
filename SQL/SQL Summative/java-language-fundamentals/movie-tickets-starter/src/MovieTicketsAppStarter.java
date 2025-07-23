import java.util.Scanner;

/*
    Java console application used to demonstrate methods in Java.
    The scenario is to mimic the purchase of movie tickets at
    the theater.

    We will prompt the user for inputs (movie, time, adult and child
    tickets) then display a summary of the purchase.

    This is the starter code and we'll build upon this in the demo.
 */
public class MovieTicketsAppStarter {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("==== Welcome to the Theater ====");
        System.out.println("Please enter the ticket info below.\n");

        // Prompt user for movie title
        System.out.print("What movie would you like to see? ");
        String movie = console.nextLine();

        // Prompt user for movie time (1pm, 2:30pm, etc.)
        // Notes:   Keeping it simple to avoid time validation challenges (later module)
        //          Current version does not validate for expected values
        System.out.print("There are 3 matinees available: 1pm, 2:30pm, 4pm\nWhat time?");
        String movieTime = console.nextLine();

        // Prompt user for # of adult tickets
        System.out.print("# of Adult Tickets? ");
        String adultTixString = console.nextLine();
        int adultTix = Integer.parseInt(adultTixString);

        // Prompt user for # of child tickets
        System.out.print("# of Child Tickets? ");
        String childTixString = console.nextLine();
        int childTix = Integer.parseInt(childTixString);

        // Calculate cost: Matinee Adult: $11.75, Child: $8.25
        double totalCost = adultTix * 11.75 + childTix * 8.25;

        System.out.println("\nPurchase Complete! Summary: ");
        System.out.println("Movie:\t\t"+movie);
        System.out.println("Time:\t\t"+movieTime);
        System.out.println("Adult Tix:\t"+adultTix);
        System.out.println("Child Tix:\t"+childTix);
        System.out.println("Total Cost:\t$"+totalCost);

        System.out.println("\nThanks!  Enjoy the show!");

    }

    //Method Signature:
    //access-modifier {static} return-type method-name(parameter(s)) { ... }
}
