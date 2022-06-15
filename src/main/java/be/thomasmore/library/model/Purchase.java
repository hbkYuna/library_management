package be.thomasmore.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Purchase {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name = "order_generator", sequenceName = "order_seq", allocationSize = 1)
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Book> books;

    private boolean isFinalised;

    public Purchase(User user, List<Book> books) {
        this.user = user;
        this.books = books;
        this.isFinalised = false;
    }

    public Purchase() {
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public boolean isFinalised() {
        return isFinalised;
    }

    public void setFinalised(boolean finalised) {
        isFinalised = finalised;
    }
}
