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
    }

    public Map<Integer, Integer> getBookQuantity(List<Book> books) {
        Map<Integer, Integer> quantity = new HashMap<>();
        for (Book book : books) {
            List<Integer> book_copy = jdbi.withHandle(handle ->
                    handle.createQuery("select id from book_copy where book_id = :id")
                            .bind("id", book.getId())
                            .mapTo(Integer.class)
                            .list());
            quantity.put(book.getId(), book_copy.size());
        }
        return quantity;
    }
}