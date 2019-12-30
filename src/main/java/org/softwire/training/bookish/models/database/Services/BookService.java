package org.softwire.training.bookish.models.database.Services;

import org.softwire.training.bookish.models.database.Models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<Book> getTenBooks(Integer limit, Integer offset) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM book_titles LIMIT :limit OFFSET :offset")
                .bind("limit",limit)
                .bind("offset",offset)
                .mapToBean(Book.class)
                .list());
    }


    public Book getBookById(Integer id) {
        return jdbi.withHandle(handle -> handle.createQuery(
                "SELECT * FROM book_titles WHERE id = :id")
                .bind("id", id)
                .mapToBean(Book.class).findOnly());
    }

}
