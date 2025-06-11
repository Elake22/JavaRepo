// This class represents a Book media type and extends the abstract Media superclass
public class Book extends Media {
    // Private fields specific to Book
    private String author;      // The author of the book
    private int pageCount;      // The number of pages in the book

    // Constructor to initialize name (from Media), author, and page count
    public Book(String name, String author, int pageCount) {
        super(name);              // Call Media constructor to set the name
        this.author = author;     // Set the author name
        this.pageCount = pageCount; // Set the number of pages
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // Implements the abstract play() method from Media
    // Simulates opening the book
    @Override
    public void play() {
        System.out.println("Opening book: " + getName());
    }

    // Implements the abstract getDescription() method from Media
    @Override
    public String getDescription() {
        return "Book by " + author + " (" + pageCount + " pages)";
    }
}
