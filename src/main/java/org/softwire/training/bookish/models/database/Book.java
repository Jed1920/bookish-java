package org.softwire.training.bookish.models.database;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String author;
    private List<Integer> bookCopys;

    @Override
    public String toString() {
        return String.format("%-5d | %-40s | %-20s", id, title, author);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookCopys(List<Integer> bookCopys) {
        this.bookCopys = bookCopys;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<Integer> getBookCopys() {
        return bookCopys;
    }
}
