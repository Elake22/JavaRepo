// Loan Caculator

public class Main { // Loan Caculator
    public static void main(String[] args) {
        double loanAmount = 25000.00; // in dollars
        double annualInterestRate = 5.5; // as a percentage
        int loanTermYears = 5;
        double monthlyPayment;

        loanAmount += 5000;

        monthlyPayment = (loanAmount * ( annualInterestRate / 100)) / 12;

        System.out.println(" Updated Loan Amount: $" + loanAmount);
        System.out.println(" Estimated Monthly Intrest Payment: $" + monthlyPayment);
    }
}