// Book.java
public class Book {
    String title;
    String author;
    boolean isAvailable;

    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
    // Method to borrow the book
    public void borrowBook() {
        isAvailable = false;
    }

    // Display book details
    public void displayStatus() {
        System.out.println("Book: " + title + " by " + author + " (Available: " + isAvailable + ")");
    }

}
