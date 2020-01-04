package org.softwire.training.bookish.models.database.Controllers;

import org.softwire.training.bookish.models.database.Models.Book;
import org.softwire.training.bookish.models.database.Models.BookModel;
import org.softwire.training.bookish.models.database.Models.NewBook;
import org.softwire.training.bookish.models.database.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin(origins = "http://localhost:3000")
    public List<BookModel> getPageOfBooks(@PathVariable("id") Integer pageNumber){
        Integer offset = (pageNumber-1) * pageLimit;
        List<BookModel> books = bookService.getTenBooks(pageLimit,offset);
        return books;
    }

    @RequestMapping(value = "/bookId{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:3000")
    public BookModel getBook(@PathVariable("id") Integer bookId){
        BookModel book = bookService.getTitleById(bookId);
        return book;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public BookModel addBook(@ModelAttribute NewBook newBook){
        BookModel book = bookService.addBook(newBook);
        return book;
    }

    @RequestMapping(value = "/delete_copy", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteCopy(@ModelAttribute Integer copyId){
        bookService.deleteBookCopy(copyId);
    }

    @RequestMapping(value = "/delete_title", method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteTitle(@ModelAttribute Integer titleId){
        bookService.deleteBookTitle(titleId);
    }
}
