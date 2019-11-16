package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id,title,author FROM test.allBooks")
                        .mapToBean(Book.class)
                        .list());
    }

    public Book getBook(int bookId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id,title,author FROM test.allBooks WHERE id = :id")
                        .bind("id", bookId)
                        .mapToBean(Book.class)
                        .findFirst().get());
    }

    public List<Integer> getQuantity(int bookId){
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id FROM test.uniqueTitle WHERE book_id= :id")
                        .bind("id", bookId)
                        .mapTo(Integer.class)
                        .list());
    }
}
