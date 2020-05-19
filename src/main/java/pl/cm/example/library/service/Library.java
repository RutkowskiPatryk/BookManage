package pl.cm.example.library.service;

import pl.cm.example.library.exception.BookAlreadyExistsException;
import pl.cm.example.library.exception.BookLoadException;
import pl.cm.example.library.exception.BookSaveException;
import pl.cm.example.library.exception.EmptyBookQueueException;
import pl.cm.example.library.exception.NoSuchBookException;
import pl.cm.example.library.model.Author;
import pl.cm.example.library.model.Book;
import pl.cm.example.library.model.comparator.BooksYearComparator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Library {

    private List<Book> books = new ArrayList<>();

    private Queue<Book> toRead = new PriorityQueue<>(new BooksYearComparator().reversed());

    public void loadBooks(String file) throws BookLoadException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int number = Integer.parseInt(br.readLine());
            while (number-- > 0) {
                Book book = new Book();
                Author author = new Author();
                author.setName(br.readLine());
                author.setSurname(br.readLine());
                book.setAuthor(author);
                book.setTitle(br.readLine());
                book.setIsbn(Long.parseLong(br.readLine()));
                book.setPublishYear(Integer.parseInt(br.readLine()));
                book.setCover(Book.Cover.valueOf(br.readLine().toUpperCase()));
                books.add(book);
            }
        } catch (IOException ex) {
            throw new BookLoadException(ex);
        }
    }

    public void printBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void addBook(Book book) throws BookAlreadyExistsException {
        if (books.contains(book)) {
            throw new BookAlreadyExistsException("Book " + book.getTitle() + " already registered");
        }
        books.add(book);
    }

    public void remove(Book book) throws NoSuchBookException {
        if (!books.contains(book)) {
            throw new NoSuchBookException("Book " + book.getTitle() + " is not registered");
        }
        toRead.remove(book);
        books.remove(book);
    }

    public void saveBooks(String file) throws BookSaveException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(Integer.toString(books.size()));
            bw.newLine();
            for (Book book : books) {
                bw.write(book.getAuthor().getName());
                bw.newLine();
                bw.write(book.getAuthor().getSurname());
                bw.newLine();
                bw.write(book.getTitle());
                bw.newLine();
                bw.write(Long.toString(book.getIsbn()));
                bw.newLine();
                bw.write(Integer.toString(book.getPublishYear()));
                bw.newLine();
                bw.write(book.getCover().name().toLowerCase());
                bw.newLine();
            }
        } catch (IOException ex) {
            throw new BookSaveException(ex);
        }
    }

    public void saveBooksBinary(String file) throws BookSaveException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(books);
        } catch (IOException ex) {
            throw new BookSaveException(ex);
        }
    }

    public void loadBooksBinary(String file) throws BookLoadException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new BookLoadException(ex);
        }
    }

    public List<Book> fetchBooksFrom(int year) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublishYear() == year) {
                list.add(book);
            }
        }
        return list;
    }

    public Set<Author> fetchAuthors() {
        Set<Author> authors = new HashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }

    public void removeBooksWith(Book.Cover cover) {
        for (Iterator<Book> it = books.iterator(); it.hasNext();) {
            Book book = it.next();
            if (book.getCover() == cover) {
                it.remove();
                toRead.remove(book);
            }
        }
    }

    public void removeBooksBefore(int year) {
        for (Iterator<Book> it = books.iterator(); it.hasNext();) {
            Book book = it.next();
            if (book.getPublishYear() < year) {
                it.remove();
                toRead.remove(book);
            }
        }
    }

    public List<Book> fetchSortedBooksBy(Author author) {
        List<Book> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                list.add(book);
            }
        }
        Collections.sort(list);
        return list;
    }

    public List<Book> fetchSortedBooksBy(String name, String surname) {
        return fetchSortedBooksBy(new Author(name, surname));
    }

    public List<Book> fetchBooksSortedByYear() {
        List<Book> list = new ArrayList<>(books);
        Collections.sort(list, new BooksYearComparator());
        return list;
    }

    public SortedMap<Author, List<Book>> fetchAuthorsWithBooks() {
        SortedMap<Author, List<Book>> map = new TreeMap<>();
        for (Author author : fetchAuthors()) {
            map.put(author, fetchSortedBooksBy(author));
        }
        return map;
    }

    public void addBookToRead(Book book) throws BookAlreadyExistsException, NoSuchBookException {
        if (toRead.contains(book)) {
            throw new BookAlreadyExistsException();
        }
        if (!books.contains(book)) {
            throw new NoSuchBookException();
        }
        toRead.add(book);
    }

    public Book fetch() throws EmptyBookQueueException{
        if (toRead.isEmpty()) {
            throw new EmptyBookQueueException();
        }
        return toRead.poll();
    }

}
