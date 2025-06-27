package domain;

import model.book;
import data.LibraryRepository;

import java.util.List;
import java.util.Optional;

/**
 * The LibraryService contains business logic for managing books.
 * It delegates data storage to the repository and ensures validation rules are met.
 */
public class LibraryService {

    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    // Adds a new book if valid and not already present
    public boolean add(book book) {
        if (!isValid(book)) return false;
        if (repository.findByIsbn(book.isbn()).isPresent()) return false;
        repository.add(book);
        return true;
    }

    // Updates an existing book if valid and present
    public boolean update(String isbn, book updatedBook) {
        if (!isValid(updatedBook)) return false;
        return repository.update(isbn, updatedBook);
    }

    // Deletes a book by its ISBN
    public boolean delete(String isbn) {
        return repository.deleteByIsbn(isbn);
    }

    // Returns all books
    public List<book> getAll() {
        return repository.getAll();
    }

    // Finds a book by ISBN
    public Optional<book> findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    // Simple validation logic
    private boolean isValid(book book) {
        return book != null
                && !book.category().isBlank()
                && book.shelfNumber() > 0 && book.shelfNumber() <= 250
                && book.position() > 0 && book.position() <= 250
                && !book.author().isBlank()
                && !book.isbn().isBlank()
                && book.yearPublished() > 0 && book.yearPublished() <= java.time.Year.now().getValue();
    }
}
