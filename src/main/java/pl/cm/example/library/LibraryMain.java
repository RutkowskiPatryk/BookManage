package pl.cm.example.library;

import pl.cm.example.library.exception.BookException;
import pl.cm.example.library.model.Author;
import pl.cm.example.library.model.Book;
import pl.cm.example.library.service.Library;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryMain {

    private static final Logger log = Logger.getLogger(LibraryMain.class.getName());

    public static void main(String[] args) {
        Library library = new Library();

        try {
            library.loadBooks("books.txt");
            library.printBooks();
            library.addBook(new Book(new Author("Jarosław", "Grzędowicz"),
                    "Pan Lodowego Ogrodu",
                    9788371749311L,
                    2011,
                    Book.Cover.HARD));
            library.remove(new Book(new Author("Jarosław", "Grzędowicz"),
                    "Pan Lodowego Ogrodu",
                    9788371749311L,
                    2011,
                    Book.Cover.HARD));
            library.saveBooks("books2.txt");
            library.saveBooksBinary("books.bin");
            library.loadBooksBinary("books.bin");
            for (Book book : library.fetchBooksFrom(2014)) {
                System.out.println(book);
            }
            for (Author author : library.fetchAuthors()) {
                System.out.println(author);
            }
            library.removeBooksWith(Book.Cover.SOFT);
        } catch (BookException ex) {
            log.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

}
