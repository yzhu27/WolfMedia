package config;

/**
 * Return the status of queries and updates run
 */
public class Result {
    // if SQL operation executed successfully or not
    boolean success;
    // why operation failed
    String errorMessage;

    public Result(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }
}