package Library;

import data.LibraryRepository;
import domain.LibraryService;
import model.book;
import view.LibraryView;

import java.util.List;

public class LibraryApp {

    public static void main(String[] args ) {
        // Initialize repository, service, and view
        LibraryRepository repository = new LibraryRepository();
        LibraryService service = new LibraryService(repository);
        LibraryView view = new LibraryView();

        boolean running = true;

        while (running) {
            view.showMenu();
            int choice = view.getMenuChoice();

            switch (choice) {
                case 0 -> {
                    running = false;
                    view.showMessage("Goodbye");
                }
                case 1 -> {
                    String category = view.prompt("Enter category to search");
                    List<book> found = service.findBooksByCategory(category);
                    view.showBooks(found.stream().map(b -> b.title() + " by " + b.author())
                            .toList(), "Books in '" + category + "'");
                }
                case 2 -> {
                    String title = view.prompt("Title");
                    String author = view.prompt("Author");
                    String isbn = view.prompt("ISBN");
                    int year = Integer.parseInt(view.prompt("Year Published"));
                    String category = view.prompt("Category");
                    int shelf = Integer.parseInt(view.prompt("Shelf Number"));
                    int position = Integer.parseInt(view.prompt("Position"));

                    book newBook = new book(title, category, shelf, position, year, author, isbn);

                    boolean added = service.add(newBook);
                    view.showMessage(added ? "Book added." : "Book already exists or invalid.");
                }
                case 3 -> {
                    String isbn = view.prompt("Enter ISBN of book to update");
                    String title = view.prompt("New Title");
                    String author = view.prompt("New Author");
                    int year = Integer.parseInt(view.prompt("New Year Published"));
                    String category = view.prompt("New Category");
                    int shelf = Integer.parseInt(view.prompt("New Shelf Number"));
                    int position = Integer.parseInt(view.prompt("New Position"));

                    book updatedBook = new book(title, category, shelf, position, year, author, isbn);
                    boolean updated = service.update(isbn, updatedBook);
                    view.showMessage(updated ? "Book updated." : "Book not found or invalid.");

                }
                case 4 -> {
                    String isbn = view.prompt("Enter ISBN of book to remove");
                    boolean deleted = service.delete(isbn);
                    view.showMessage(deleted ? "Book removed." : "Book not found.");
                }

                default -> view.showMessage("Invalid option. Please choose between 0–4.");
                }
            }
        view.closeScanner();
    }
}


