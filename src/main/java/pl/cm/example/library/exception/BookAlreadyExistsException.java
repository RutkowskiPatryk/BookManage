package pl.cm.example.library.exception;

public class BookAlreadyExistsException extends BookException {

    public BookAlreadyExistsException() {
    }

    public BookAlreadyExistsException(String message) {
        super(message);
    }

    public BookAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
