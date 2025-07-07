package model;


public class Product {
    private String productID;         // Product ID
    private String productName;    // Name of the product
    private int quantity;          // Quantity available in inventory
    private double price;          // Price per unit

    // Constructor
    public Product(String productID, String productName, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    // Getters and Setters


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // ToString for display purposes
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Qty: %d | Price: $%.2f",
                productID, productName, quantity, price);
    }
}