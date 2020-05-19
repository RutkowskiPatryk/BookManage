package pl.cm.example.library.model.comparator;

import pl.cm.example.library.model.Book;

import java.util.Comparator;

public class BooksYearComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        int value = o1.getPublishYear() - o2.getPublishYear();
        if (value == 0) {
            value = o1.getTitle().compareTo(o2.getTitle());
        }
        if (value == 0) {
            value = o1.getAuthor().compareTo(o2.getAuthor());
        }
        return value;
    }
}
