package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;

import java.util.List;

public class BookPageModel {

    private Book book;
    private List<Integer> bookCopies;
    private Integer id;
    private String title;
    private String author;
    private Integer quantity;

    public BookPageModel(Book book, List<Integer> bookCopies){
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        quantity = bookCopies.size();
    }

    public Book getBook() {
        return book;
    }

    public List<Integer> getBookCopies() {
        return bookCopies;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
