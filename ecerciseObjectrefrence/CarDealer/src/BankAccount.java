// BankAccount.java
public class BankAccount {
    String owner;
    double balance;

    // Constructor
    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    // Deposit method
    public void deposit(double amount) {
        balance += amount;
    }

    // Display method
    public void displayBalance() {
    System.out.println("Account Owner: " + owner + ", Balance: $" + balance);
    }
}
