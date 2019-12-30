package org.softwire.training.bookish.models.database.Models;

public class Book {

    private Integer id;
    private String title;
    private String author;

    @Override
    public String toString() {
        return String.format("| %d | %s | %s|", id, title , author);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
