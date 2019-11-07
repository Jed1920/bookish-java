package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddService extends DatabaseService {

    public void addNewTitle(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO book (title, author) VALUES (:title, :author)")
                        .bind("title", book.getTitle())
                        .bind("author", book.getAuthor())
                        .execute()
        );
    }

}