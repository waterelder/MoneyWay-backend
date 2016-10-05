package xyz.trackbuck.util;

/**
 * Created by lex on 29.09.16.
 */
public class NotFoundSearchTypeException extends RuntimeException {
    public NotFoundSearchTypeException() {
    }

    public NotFoundSearchTypeException(String message) {
        super(message);
    }
}
