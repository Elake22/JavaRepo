// Locker internal data and tracking

public class LockerManager {
    private String[] lockers;
    private static final int TOTAL_LOCKERS = 10;

    // Aligns internal index to start at 1 for display alignment
    public LockerManager() {
        lockers = new String[TOTAL_LOCKERS + 1];
    }

    // 1. Method: Check for available locker
    public boolean hasAvailableLocker() {
        for (int i = 1; i <= TOTAL_LOCKERS; i++) { // Starts at 1
            if (lockers[i] == null) return true;
        }
        return false;
    }
    // 2. Method: Rent the first open locker and assigns PIN
    public int rentLocker() {
        for (int i = 1; i <= TOTAL_LOCKERS; i++) {
            if (lockers[i] == null) {
                String pin = generatePin();
                lockers[i] = pin;
                return i;
            }
        }
        return -1; // No Locker open
    }
    // 3. Method: Access a locker with correct PIN
    public boolean accessLocker(int lockerNumber, String pin) {
        if (lockerNumber >= 1 && lockerNumber <= TOTAL_LOCKERS) {
            return pin.equals(lockers[lockerNumber]);
        }
        return false;
    }
    // 4. Method: Release a locker if PIN matches
    public boolean releaseLocker(int lockerNumber, String pin) {
        if (accessLocker(lockerNumber, pin)) {
            lockers[lockerNumber] = null; // This frees the locker
            return true;
        }
        return false;

    }
    // 5. Method: Get PIN for testing
    public String getLockerPin(int lockerNumber) {
        if (lockerNumber >= 1 && lockerNumber <= TOTAL_LOCKERS) {
            return lockers[lockerNumber];
        }
        return null;
    }
    // 6. Method: Generate a 4-digit random PIN
    private String generatePin() {
        int pin = (int) (Math.random() * 9000) + 1000; // ensures 4 digits
        return String.format("%04d", pin);
    }
    // 7. Returns the total number of lockers
    public int getTotalLockers() {
        return TOTAL_LOCKERS;
    }
    // 8. Helper method to check for availability
    public boolean isLockerAvailable(int lockerNumber) {
        if (lockerNumber >= 1 && lockerNumber <= TOTAL_LOCKERS) {
            return lockers[lockerNumber] == null;
        }
        return false;
    }
}




