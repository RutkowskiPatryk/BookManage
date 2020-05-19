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
public class Author implements Comparable<Author>, Serializable {

    private String name;

    private String surname;

    @Override
    public int compareTo(Author o) throws NullPointerException {
        int value = surname.compareTo(o.surname);
        if (value == 0) {
            value = name.compareTo(o.name);
        }
        return value;
    }
}
