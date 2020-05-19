package pl.cm.example.library.exception;

public class BookLoadException extends BookException {

    public BookLoadException() {
    }

    public BookLoadException(String message) {
        super(message);
    }

    public BookLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookLoadException(Throwable cause) {
        super(cause);
    }
}
