package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.AllBooksPageModel;
import org.softwire.training.bookish.models.page.BookPageModel;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/allbooks")
public class BookController {

    private final BookService bookservice;

    @Autowired
    public BookController(BookService bookservice) {
        this.bookservice = bookservice;
    }

    @RequestMapping("")
    ModelAndView allBookList() {
        List<Book> allBooks = bookservice.getAllBooks();

        AllBooksPageModel allBooksPageModel = new AllBooksPageModel();
        allBooksPageModel.setAllBooks(allBooks);
        return new ModelAndView("allbooks", "model", allBooksPageModel);
    }

    @RequestMapping("/{id}")
    ModelAndView indvBook(@RequestParam int id){
        Book book = bookservice.getBook(id);
        List<Integer> bookCopies = bookservice.getQuantity(id);

        BookPageModel bookPageModel = new BookPageModel(book,bookCopies);
        return new ModelAndView("book","model",bookPageModel);
    }
}
