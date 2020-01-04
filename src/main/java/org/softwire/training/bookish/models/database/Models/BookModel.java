package org.softwire.training.bookish.models.database.Models;

import java.util.List;

public class BookModel {

    private Book book;
    private List<Integer> copyIds;

    public BookModel(Book book, List<Integer> copyIds){
        this.book = book;
        this.copyIds = copyIds;
    }

    public Integer getId() {
        return book.getId();
    }

    public String getTitle() {
        return book.getTitle();
    }

    public String getAuthor() {
        return book.getAuthor();
    }

    public List<Integer> getCopyIds() {
        return copyIds;
    }
}
