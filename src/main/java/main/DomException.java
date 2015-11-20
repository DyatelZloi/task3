package main.java.main;

/**
 * Created by DiZi on 20.11.2015.
 */
public class DomException extends Exception {
    public DomException(String message) {
        super(message);
    }

    public DomException(String message, Throwable cause) {
        super(message, cause);
    }
}
