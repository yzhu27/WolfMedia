package config;

/**
 * Return the status of queries and updates run
 */
public class Result {
    // if SQL operation executed successfully or not
    public boolean success;
    // why operation failed
    public String errorMessage;

    public Result(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }
}