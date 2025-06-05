//Locker action results
public class Result {
    // Indicates whether the operation was successful
    private final boolean success;
    private final String message; //Message about the result (success or failure)

    public Result(boolean success, String message) { // Builds a Result with success status and message
        this.success = success;
        this.message = message;
    }
    // Getter for success
    public boolean isSuccess() {
        return success;
    }
    // Getter for message
    public String getMessage() {
        return message;
    }
}