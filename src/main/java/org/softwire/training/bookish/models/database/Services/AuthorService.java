package org.softwire.training.bookish.models.database.Services;

import javafx.scene.chart.PieChart;
import org.softwire.training.bookish.models.database.Models.Book;
import org.softwire.training.bookish.models.database.Models.BookModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService extends DatabaseService {

    public List<BookModel> getAuthorBooks(String author) {
        List<Book> books = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM book_titles WHERE author = :author")
                .bind("author", author)
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
}
