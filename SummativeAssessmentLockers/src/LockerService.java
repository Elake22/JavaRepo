public class LockerService {
    private final LockerManager manager;

    public LockerService(LockerManager manager) {
        this.manager = manager;
    }

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

    public Result rentLocker() {
        if (!manager.hasAvailableLocker()) {
            return new Result(false, "No Locker Available.");
        }
        int lockerNum = manager.rentLocker();
        String pin = manager.getLockerPin(lockerNum);
        return new Result(true, "Locker " + lockerNum + " rented. PIN: " + pin);
    }

    public Result accessLocker(int lockerNumber, String pin) {
        if (manager.accessLocker(lockerNumber, pin)) {
            return new Result(true, "Access Granted!");
        }
        return new Result(false, "Access Denied.");
    }

    public Result releaseLocker(int lockerNumber, String pin) {
        if (manager.releaseLocker(lockerNumber, pin)) {
            return new Result(true, "Locker released.");
        }
        return new Result(false, "Incorrect PIN or locker number.");
    }
}
