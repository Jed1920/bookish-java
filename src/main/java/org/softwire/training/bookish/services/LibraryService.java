package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book")
                        .mapToBean(Book.class)
                        .list());
    }

    public Book getBook(int bookId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book WHERE id = :id")
                        .bind("id", bookId)
                        .mapToBean(Book.class)
                        .findFirst().get());
    }

    public List<Integer> getQuantity(int bookId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id FROM book_copy WHERE book_id = :id")
                        .bind("id", bookId)
                        .mapTo(Integer.class)
                        .list());
    }
}