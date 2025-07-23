import java.util.Scanner;

public class AquariumTracker {
    public static void main(String[] args) {
        String species;
        String commonName;
        double maxTemp = 100.0;
        double minTemp = 0.0;
        String diet;
        boolean validInput=false;

        Scanner console = new Scanner(System.in);

        System.out.println("Enter the information for your fish.");
        System.out.print("Species Name: ");
        species = console.nextLine();

        System.out.print("Common Name: ");
        commonName = console.nextLine();

        do {
            System.out.print("Maximum temperature: ");
            String temp = console.nextLine();
            try{
                maxTemp = Double.valueOf(temp);
                validInput=true;
            }
            catch (Exception e) {
                System.out.println("Enter a valid temperature");
                validInput = false;
            }
        } while(!validInput);

        do {
            System.out.print("Minimum temperature: ");
            String temp = console.nextLine();
            try{
                minTemp = Double.valueOf(temp);
                validInput = true;
            }
            catch (Exception e) {
                System.out.println("Enter a valid temperature");
                validInput = false;
            }
        } while(!validInput);

        System.out.println("Diet: ");
        diet = console.nextLine();

        System.out.println("Thank you for the input, here is the summary.");
        System.out.println("Species name: " + species);
        System.out.println("Common name: " + commonName);
        System.out.println("Average Temperature: " + ((maxTemp + minTemp)/2.0));
    }
}
