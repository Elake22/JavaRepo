import java.util.ArrayList;

public class LockerManager {
    private ArrayList<Locker> lockers; // list to store Locker objects

    public LockerManager() {
        lockers = new ArrayList<>(); // initialize the locker list
    }

    public void addLocker(String lockerId) { // create and add new locker
        if (getLocker(lockerId) != null) {
            System.out.println("Locker ID already exists.");
            return;
        }
        lockers.add(new Locker(lockerId));
        System.out.println("Locker " + lockerId + " added.");
    }

    public Locker getLocker(String lockerId) { // find locker by ID
        for (Locker locker : lockers) {
            if (locker.getLockerId().equals(lockerId)) {
                return locker;
            }
        }
        return null; // return null if not found
    }

    public void removeLocker(String lockerId) { // remove locker by ID
        Locker locker = getLocker(lockerId);
        if (locker != null) {
            lockers.remove(locker);
            System.out.println("Locker " + lockerId + " removed.");
        } else {
            System.out.println("Locker not found.");
        }
    }

    public void displayAllLockers() { // display each locker status
        if (lockers.isEmpty()) {
            System.out.println("No lockers available.");
        } else {
            for (Locker locker : lockers) {
                System.out.println(locker);
            }
        }
    }
}
