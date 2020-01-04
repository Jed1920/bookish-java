package org.softwire.training.bookish.models.database.Services;

import org.softwire.training.bookish.models.database.Models.Book;
import org.softwire.training.bookish.models.database.Models.BookModel;
import org.softwire.training.bookish.models.database.Models.NewBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<BookModel> getTenBooks(Integer limit, Integer offset) {
        List<Book> books = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM book_titles LIMIT :limit OFFSET :offset")
                .bind("limit", limit)
                .bind("offset", offset)
                .mapToBean(Book.class)
                .list());
        List<BookModel> bookModels = new ArrayList<>();
        for (Book book : books) {
            List<Integer> copyIds = jdbi.withHandle(handle -> handle.createQuery(
                    "SELECT copy_id FROM book_copies WHERE title_id = :id")
                    .bind("id", book.getId())
                    .mapTo(Integer.class).list());
            BookModel bookModel = new BookModel(book, copyIds);
            bookModels.add(bookModel);
        }
        return bookModels;
    }

    public BookModel getTitleById(Integer id) {
        Book book = jdbi.withHandle(handle -> handle.createQuery(
                "SELECT * FROM book_titles WHERE id = :id")
                .bind("id", id)
                .mapToBean(Book.class).findOnly());
        List<Integer> copyIds = jdbi.withHandle(handle -> handle.createQuery(
                "SELECT copy_id FROM book_copies WHERE title_id = :id")
                .bind("id", id)
                .mapTo(Integer.class).list());
        BookModel bookModel = new BookModel(book,copyIds);
        return bookModel;

    }

    private List<Integer> getIdByTitleAuthor(String title, String author) {
        List<Integer> allIds = jdbi.withHandle(handle -> handle.createQuery(
                "SELECT id FROM book_titles WHERE title = :title and author = :author")
                .bind("title", title)
                .bind("author", author)
                .mapTo(Integer.class).list());
        return allIds;
    }

    public BookModel addBook(NewBook book) {
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
        return getTitleById(finalId);
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

