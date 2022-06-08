package ua.lviv.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 200, message = "Author should be between 2 and 200 characters")
    private String author;
    private int year;

    private int person_id;

    public Book() {
    }

    public Book(int id, String title, String author, int year, int person_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
