package org.softwire.training.bookish.models.page;
import org.softwire.training.bookish.models.database.Book;
import java.util.List;

public class LibraryPageModel {

    private List<Book> allBooks;

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

}
