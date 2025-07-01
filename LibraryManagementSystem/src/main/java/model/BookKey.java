package model;

import java.util.Objects;


  //BookKey is a composite key used to uniquely identify a book's location

public record BookKey(
        String category,    // The section or genre (e.g., Fiction)
        int shelfNumber,    // Shelf number within the category
        int position        // Position on the shelf
) {

     //Provides a readable string version of the key,

    @Override
    public String toString() {
        return category + "-" + shelfNumber + "-" + position;
    }

     // Override equals to ensure case-insensitive matching for category,

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookKey key)) return false;
        return shelfNumber == key.shelfNumber &&
                position == key.position &&
                category.equalsIgnoreCase(key.category);
    }
     // This ensures correct behavior in HashMap lookups.

    @Override
    public int hashCode() {
        return Objects.hash(category.toLowerCase(), shelfNumber, position);
    }
}
