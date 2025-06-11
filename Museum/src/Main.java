import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the name of the artifact: ");
        String artifactName = input.nextLine();

        System.out.print( "Enter year of discovery: ");
        int year = Integer.parseInt(input.nextLine());

        System.out.print("Enter discoverer's first name: ");
        String dFirst = input.nextLine();
        System.out.print("Enter discoverer's last name: ");
        String dLast = input.nextLine();
        System.out.print("Enter discoverer's specialty: ");
        String dSpecialty = input.nextLine();
        Person discoverer = new Person(dFirst, dLast, dSpecialty);


        System.out.print("Is the discoverer also the curator? (Y/N): ");
        String samePerson = input.nextLine().trim().toUpperCase();
        Person curator = discoverer;


        if (!samePerson.equals("Y")) {
            System.out.print("Enter curator's first name: ");
            String cFirst = input.nextLine();
            System.out.print("Enter curator's last name: ");
            String cLast = input.nextLine();
            System.out.print("Enter curator's specialty: ");
            String cSpecialty = input.nextLine();
            curator = new Person(cFirst, cLast, cSpecialty);
        }


        System.out.println();
        System.out.println("Artifact:  " + artifactName + " (" + year + ")");
        System.out.println("Discoverer:  " + discoverer);
        System.out.println("Curator:  " + curator);
    }
}

