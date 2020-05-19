package pl.cm.example.library.exception;

public class NoSuchBookException extends BookException {

    public NoSuchBookException() {
    }

    public NoSuchBookException(String message) {
        super(message);
    }

    public NoSuchBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBookException(Throwable cause) {
        super(cause);
    }
}
