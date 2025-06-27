package Library;

import domain.LibraryService;
import model.book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.LibraryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void setUp() {
        service = new LibraryService(new LibraryRepository()); // in-memory stub for tests
    }

    @Test
    void shouldAddValidBook() {
        book book = new book("Fiction", 2, 3, 2005, "Octavia Butler", "ISBN-001");
        assertTrue(service.add(book));
    }

    @Test
    void shouldNotAddDuplicateBook() {
        book book = new book("History", 1, 1, 1999, "Howard Zinn", "ISBN-002");
        service.add(book);
        assertFalse(service.add(book)); // duplicate ISBN
    }

    @Test
    void shouldNotAddInvalidBook() {
        book invalid = new book("", 0, -1, 2025, "", ""); // invalid fields
        assertFalse(service.add(invalid));
    }

    @Test
    void shouldUpdateBook() {
        book original = new book("Sci-Fi", 5, 10, 2010, "Ursula K. Le Guin", "ISBN-003");
        service.add(original);

        book updated = new book("Sci-Fi", 5, 10, 2012, "Ursula K. Le Guin", "ISBN-003");
        assertTrue(service.update("ISBN-003", updated));
        assertEquals(2012, service.findByIsbn("ISBN-003").get().yearPublished());
    }

    @Test
    void shouldDeleteBook() {
        book book = new book("Poetry", 2, 5, 2011, "Langston Hughes", "ISBN-004");
        service.add(book);
        assertTrue(service.delete("ISBN-004"));
        assertTrue(service.findByIsbn("ISBN-004").isEmpty());
    }

    @Test
    void shouldReturnAllBooks() {
        service.add(new book("Drama", 3, 3, 2001, "Lorraine Hansberry", "ISBN-005"));
        service.add(new book("Fiction", 4, 7, 1984, "James Baldwin", "ISBN-006"));
        assertEquals(2, service.getAll().size());
    }
}
