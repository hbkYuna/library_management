package be.thomasmore.library_mgm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    private int id;
    private String title;
    private String language;
    private int pages;
    private String author;
    private int year;

    public Book(int id, String title, String language, int pages, String author, int year) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.pages = pages;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public int getPages() {
        return pages;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
