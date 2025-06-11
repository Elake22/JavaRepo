import java.util.Scanner;

class Person{
    private String firstName;
    private String lastname;
    private String primarySpecialty;

    public Person(String firstName, String lastname, String primarySpecialty) {
        this.firstName = firstName;
        this.lastname = lastname;
        this. primarySpecialty = primarySpecialty;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPrimarySpecialty() {
        return primarySpecialty;
    }


    @Override
    public String toString() {
        return  firstName + " " + lastname + ", " + primarySpecialty;
    }
}