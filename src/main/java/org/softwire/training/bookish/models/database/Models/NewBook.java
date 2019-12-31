package org.softwire.training.bookish.models.database.Models;

public class NewBook {

    private String title;
    private String author;

    @Override
    public String toString() {
        return String.format("| %s | %s|", title , author);
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
