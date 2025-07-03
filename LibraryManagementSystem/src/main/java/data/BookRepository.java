package data;

import model.book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    void add(book book);
    boolean update(String isbn, book book);
    boolean deleteByIsbn(String isbn);
    List<book> getAll();
    Optional<book> findByIsbn(String isbn);
}
