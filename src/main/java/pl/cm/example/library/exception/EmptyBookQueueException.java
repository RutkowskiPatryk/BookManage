package pl.cm.example.library.exception;

public class EmptyBookQueueException extends BookException {
    public EmptyBookQueueException() {
    }

    public EmptyBookQueueException(String message) {
        super(message);
    }

    public EmptyBookQueueException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyBookQueueException(Throwable cause) {
        super(cause);
    }
}
