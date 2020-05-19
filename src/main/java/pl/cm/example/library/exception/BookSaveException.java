package pl.cm.example.library.exception;

public class BookSaveException extends BookException {
    public BookSaveException() {
    }

    public BookSaveException(String message) {
        super(message);
    }

    public BookSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookSaveException(Throwable cause) {
        super(cause);
    }
}
