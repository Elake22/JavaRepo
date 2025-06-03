public class LockerManager {
    private String[] lockers;
    private static final int TOTAL_LOCKERS = 10;

    public LockerManager() {
        lockers = new String[TOTAL_LOCKERS];
    }
    // Method: Check for available locker
    public  boolean hasAvailableLocker() {
        for (String locker : lockers) {
            if (locker == null) return true;
        }
        return false;
    }
    // Method: Rent the first open locker
    public int rentLocker() {
        for (int i = 0; i < lockers.length; i++) {
            if (lockers[i] == null) {
                String pin = generatePin();
                lockers[i] = pin;
                return i;
            }
        }
        return -1; // No Locker open
    }
    // Method: Access a locker with correct PIN
    public boolean accessLocker(int lockerNumber, String pin) {
        if (lockerNumber >= 0 && lockerNumber < TOTAL_LOCKERS) {
            return pin.equals(lockers[lockerNumber]);
        }
        return false;
    }
    // Method: Release a locker if PIN matches
    public boolean releaseLocker(int lockerNumber, String pin) {
        if (accessLocker(lockerNumber, pin)) {
            lockers[lockerNumber] = null;
            return true;
        }
        return false;

    }
    // Method: Get PIN for testing
    public String getLockerPin(int lockerNumber) {
        if (lockerNumber >= 0 && lockerNumber < TOTAL_LOCKERS) {
            return lockers[lockerNumber];
        }
        return null;
    }
    // Method: Generate a 4-digit random PIN
    private String generatePin() {
        int pin = (int) (Math.random() * 9000) + 1000; // ensures 4 digits
        return String.valueOf(pin);
    }

}


