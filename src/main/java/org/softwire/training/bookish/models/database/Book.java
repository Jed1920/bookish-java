package org.softwire.training.bookish.models.database;

public class Book{

    private int id;
    private String title;
    private String author;
    private int quantity;

    @Override
    public String toString(){
        return String.format("%-5d | %-40s | %-20s | %-5d", id, title,author,quantity);
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }
}
