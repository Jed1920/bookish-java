package org.softwire.training.bookish.models.database.Controllers;

import org.softwire.training.bookish.models.database.Models.Book;
import org.softwire.training.bookish.models.database.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final Integer pageLimit = 10;
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value = "/page{id}", method = RequestMethod.GET)
    public List<Book> getPageOfBooks(@PathVariable("id") Integer pageNumber){
        Integer offset = (pageNumber-1) * pageLimit;
        List<Book> books = bookService.getTenBooks(pageLimit,offset);
        return books;
    }

    @RequestMapping(value = "/bookId{id}", method = RequestMethod.GET)
    public Book getBook5(@PathVariable("id") Integer bookId){
        Book book = bookService.getBookById(bookId);
        return book;
    }
}
