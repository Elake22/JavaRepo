package model;


 //The Book record represents a book in the library.

public record book(
        String title,         // Title of the book
        String category,      // The genre or section of the book (e.g., Fiction, Science)
        int shelfNumber,      // Shelf number where the book is placed (1–250)
        int position,         // Position on the shelf (1–250)
        int yearPublished,    // Year the book was published
        String author,        // Author of the book
        String isbn           // Unique identifier (International Standard Book Number)
) {

}

