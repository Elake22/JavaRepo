// Locker Logic handler
public class LockerService {
    private final LockerManager manager;

    public LockerService(LockerManager manager) {
        this.manager = manager;
    }

    // 1. Display message with all available lockers
    public String availableLockersMessage() {
        StringBuilder sb = new StringBuilder("Available Lockers:\n");
        boolean found = false;
        for (int i = 1; i <= manager.getTotalLockers(); i++) {
            if (manager.isLockerAvailable(i)) {
                sb.append("Locker ").append(i).append(" is available.\n");
                found = true;
            }
        }
        if (!found) {
            sb.append("No lockers currently available.\n");
        }
        return sb.toString();
    }

    // 2. Rent a locker if available and return the locker number and PIN
    public Result rentLocker() {
        if (!manager.hasAvailableLocker()) {
            return new Result(false, "No Locker Available.");
        }
        int lockerNum = manager.rentLocker(); // Assign the next open locker
        String pin = manager.getLockerPin(lockerNum); // Retrieve PIN for that locker
        return new Result(true, "Locker " + lockerNum + " rented. PIN: " + pin);
    }

    // 3. Check if user can access locker with provided number and PIN
    public Result accessLocker(int lockerNumber, String pin) {
        if (manager.accessLocker(lockerNumber, pin)) {
            return new Result(true, "Access Granted!");
        }
        return new Result(false, "Access Denied.");
    }

    // 4. Attempt to release a locker using the correct PIN
    public Result releaseLocker(int lockerNumber, String pin) {
        if (manager.releaseLocker(lockerNumber, pin)) {
            return new Result(true, "Locker released.");
        }
        return new Result(false, "Incorrect PIN or locker number.");
    }

    // 5. Locker release confirmations and logic
    public Result handleLockerRelease(int lockerNumber, String pin, String confirm) {
        if (!accessLocker(lockerNumber, pin).isSuccess()) {
            return new Result(false, "Incorrect PIN or locker number. Returning to menu.");
        }

        if (confirm.equals("yes") || confirm.equals("y")) {
            return releaseLocker(lockerNumber, pin);
        } else {
            return new Result(true, "Locker release cancelled.");
        }
    }
}

