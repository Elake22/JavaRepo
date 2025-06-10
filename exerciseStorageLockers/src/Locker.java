// Locker.java - Represents an individual locker
public class Locker {
    private String lockerId; // unique identifier for each locker
    private boolean isOccupied; // tracks if locker is currently in use
    private String contents; // stores the item description

    public Locker(String lockerId) {
        this.lockerId = lockerId; // assign ID during construction
        this.isOccupied = false; // locker starts as empty
        this.contents = ""; // no contents initially
    }

    public void storeItem(String item) {
        if (!isOccupied) {
            contents = item; // store the item
            isOccupied = true; // mark locker as occupied
            System.out.println("Item stored successfully.");
        } else {
            System.out.println("Locker is already occupied.");
        }
    }

    public void retrieveItem() {
        if (isOccupied) {
            contents = ""; // clear contents
            isOccupied = false; // mark locker as free
            System.out.println("Item retrieved successfully.");
        } else {
            System.out.println("Locker is already empty.");
        }
    }

    public String getLockerId() { // getter for locker ID
        return lockerId;
    }

    @Override
    public String toString() {
        return "Locker ID: " + lockerId + ", Occupied: " + isOccupied + ", Contents: " + (isOccupied ? contents : "[empty]");
    }
}

