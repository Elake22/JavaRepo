package data;

import model.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

// Handles storing and managing book data
public class LibraryRepository {

    // List of books for general access
    private final List<book> books = new ArrayList<>();

    // Optional: quick lookup by ISBN
    private final HashMap<String, book> bookMap = new HashMap<>();

    // Adds a book to the list and map
    public void add(book book) {
        books.add(book);
        bookMap.put(book.isbn(), book); // ISBN is assumed unique
    }

    // Returns all available books
    public List<book> getAll() {
        return new ArrayList<>(books); // return copy to protect internal state
    }

    // Finds a book by ISBN
    public Optional<book> findByIsbn(String isbn) {
        return Optional.ofNullable(bookMap.get(isbn));
    }

    // Deletes a book by ISBN
    public boolean deleteByIsbn(String isbn) {
        book book = bookMap.remove(isbn);
        return book != null && books.remove(book);
    }

    // Updates book info by replacing old entry with new
    public boolean update(String isbn, book updatedBook) {
        if (!bookMap.containsKey(isbn)) return false;

        deleteByIsbn(isbn);
        add(updatedBook);
        return true;
    }
}
