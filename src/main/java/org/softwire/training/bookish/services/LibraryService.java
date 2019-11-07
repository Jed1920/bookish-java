package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book")
                        .mapToBean(Book.class)
                        .list());
    }
    public List<Book> searchAllBooks(String find) {
        String search = (String.format("%%%s%%",find));
        List<Book> searchList = jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book WHERE title LIKE :find")
                        .bind("find",search)
                        .mapToBean(Book.class)
                        .list());
        searchList.addAll(jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM book WHERE author LIKE :find")
                        .bind("find",search)
                        .mapToBean(Book.class)
                        .list()));
        try {
            Integer id = (Integer.parseInt(find));
            searchList.addAll(jdbi.withHandle(handle ->
                    handle.createQuery("SELECT * FROM book WHERE id LIKE :find")
                            .bind("find", id)
                            .mapToBean(Book.class)
                            .list()));
        }
        catch(Exception e){}

        return searchList;
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

    public void addBookCopy(int bookId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO book_copy (book_id) VALUES (:book_id)")
                        .bind("book_id", bookId)
                        .execute()
        );
    }

    public void deleteBookTitle(int id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM book_copy WHERE book_id = :id")
                        .bind("id", id)
                        .execute());
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM book WHERE id = :id")
                        .bind("id", id)
                        .execute());
    }

    public void deleteBookCopy(int id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM book_copy WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    public void editBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE book SET title = :title, author = :author WHERE id = :id")
                        .bind("title", book.getTitle())
                        .bind("author", book.getAuthor())
                        .bind("id", book.getId())
                        .execute()
        );
    }
}