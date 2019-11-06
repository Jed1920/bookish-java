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
    public void addBookCopy(Book book, int bookId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO book_copy (id, book_id) VALUES (:id, :book_id)")
                        .bind("id", book.getId())
                        .bind("book_id", bookId)
                        .execute()
        );
    }

    public void deleteBook(int bookId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM book WHERE id = :id")
                        .bind("id", bookId)
                        .execute()
        );
    }

    public void editBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE book SET (title, author) VALUES (:title, :author) WHERE id = :id")
                        .bind("title", book.getTitle())
                        .bind("author", book.getAuthor())
                        .bind("id", book.getId())
                        .execute()
        );
    }

}