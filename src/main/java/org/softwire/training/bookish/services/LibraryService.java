package org.softwire.training.bookish.services;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.controllers.LibraryController;
import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("select * from book")
                        .mapToBean(Book.class)
                        .list());
//        for (Book book : books) {
//            List<Integer> book_copy = jdbi.withHandle(handle ->
//                    handle.createQuery("select id from book_copy where book_id = :id")
//                            .bind("id", book.getId())
//                            .mapTo(Integer.class)
//                            .list());
//            book.setBookCopys(book_copy);
//        }
//        return books;
    }

    public Book getBook(int bookId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("select * from book where id = :id")
                        .bind("id", bookId)
                        .mapToBean(Book.class)
                        .findFirst().get());
    }

    public List<Integer> getQuantity(int bookId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("select id from book_copy where book_id = :id")
                            .bind("id", bookId)
                            .mapTo(Integer.class)
                            .list());
    }
}