package model;

import java.time.LocalDate;

public class PerishableProduct extends Product {

    private LocalDate expirationDate;


    public PerishableProduct(int productID, String productName, int quantity, double price, LocalDate expirationDate) {
        super(productID, productName, quantity, price);
        this.expirationDate = expirationDate;
    }
    // Getters And Setters
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Override toString for better display
    @Override
    public String toString() {
        return super.toString() + ", Expiration: " + expirationDate;
    }
}
