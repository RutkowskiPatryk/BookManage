package pl.cm.example.library.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Comparable<Book>, Serializable {

    public enum Cover {
        HARD,
        SOFT
    }

    private Author author;

    private String title;

    private long isbn;

    private int publishYear;

    private Cover cover;

    @Override
    public int compareTo(Book o) throws NullPointerException {
        int value = author.compareTo(o.author);
        if (value == 0) {
            value = title.compareTo(o.title);
        }
        if (value == 0) {
            value = publishYear - o.publishYear;
        }
        return value;
    }

}
