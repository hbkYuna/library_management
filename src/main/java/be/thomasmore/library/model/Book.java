package be.thomasmore.library.model;

import javax.persistence.*;

@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_generator")
    @SequenceGenerator(name = "book_generator", sequenceName = "book_seq", allocationSize = 1)
    @Id
    private int id;
    private String author;
    private String title;
    private int pages;
    private int year;

    private String description;

    public Book(String author, String title, String description, int pages, int year) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.year = year;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
