package org.softwire.training.bookish.models.database.Services;

import org.softwire.training.bookish.models.database.Models.Book;
import org.softwire.training.bookish.models.database.Models.NewBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<Book> getTenBooks(Integer limit, Integer offset) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM book_titles LIMIT :limit OFFSET :offset")
                .bind("limit", limit)
                .bind("offset", offset)
                .mapToBean(Book.class)
                .list());
    }

    public Book getBookById(Integer id) {
        return jdbi.withHandle(handle -> handle.createQuery(
                "SELECT * FROM book_titles WHERE id = :id")
                .bind("id", id)
                .mapToBean(Book.class).findOnly());
    }

    private List<Integer> getIdByTitleAuthor(String title, String author) {
        List<Integer> allIds = jdbi.withHandle(handle -> handle.createQuery(
                "SELECT id FROM book_titles WHERE title = :title and author = :author")
                .bind("title", title)
                .bind("author", author)
                .mapTo(Integer.class).list());
        return allIds;
    }

    public Book addBook(NewBook book) {
        List<Integer> id = getIdByTitleAuthor(book.getTitle(), book.getAuthor());
        if (id.size() == 0) {
            jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO book_titles(title, author)VALUES(:title, :author)")
                    .bind("title", book.getTitle())
                    .bind("author", book.getAuthor())
                    .execute());
            id = getIdByTitleAuthor(book.getTitle(), book.getAuthor());
        }
        Integer finalId = id.get(0);
        jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO book_copies(title_id)VALUES(:title_id)")
                .bind("title_id", finalId)
                .execute());
        return getBookById(finalId);
    }

    public void deleteBookTitle(Integer titleId) {
        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM book_titles WHERE id = :id")
                .bind("id", titleId)
                .execute());
        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM book_copies WHERE id = :id")
                .bind("id", titleId)
                .execute());
    }

    public void deleteBookCopy(Integer copyId) {
        jdbi.withHandle(handle -> handle.createUpdate("DELETE FROM book_copies WHERE id = :id")
                .bind("id", copyId)
                .execute());
    }
}

