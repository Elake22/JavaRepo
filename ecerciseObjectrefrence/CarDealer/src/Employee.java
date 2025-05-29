public class Employee {
    String name;
    static int totalEmployees = 0;

    // Constructor
    public Employee(String name) {
        this.name = name;
        totalEmployees++;
    }

    // Static method to return total employees
    public static int getTotalEmployees() {
        return totalEmployees;
    }
}