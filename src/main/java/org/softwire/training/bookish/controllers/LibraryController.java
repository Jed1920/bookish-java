package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BookModel;
import org.softwire.training.bookish.models.page.BookPageModel;
import org.softwire.training.bookish.models.page.LibraryPageModel;
import org.softwire.training.bookish.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("/library")
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping("")
    ModelAndView library() {

        List<Book> allBooks = libraryService.getAllBooks();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setAllBooks(allBooks);

        return new ModelAndView("library", "model", libraryPageModel);
    }

    @RequestMapping("/search")
    ModelAndView search(@ModelAttribute("find") String find) {

        List<Book> searchBooks = libraryService.searchAllBooks(find);

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setAllBooks(searchBooks);

        return new ModelAndView("library", "model", libraryPageModel);
    }

    @RequestMapping("{id}")
    ModelAndView quantity(@PathVariable("id") Integer bookId) {
        Book book = libraryService.getBook(bookId);
        book.setBookCopys(libraryService.getQuantity(book.getId()));


        BookModel bookModel = new BookModel(book);

        return new ModelAndView("quantity", "model", bookModel);
    }

    @RequestMapping("{id}/add-copy")
    RedirectView addCopy(@PathVariable("id") Integer bookId) {

        libraryService.addBookCopy(bookId);

        return new RedirectView("/library/{id}");
    }
    @RequestMapping("{id}/delete-copy")
    RedirectView deleteCopy(@PathVariable("id") Integer bookId, @RequestParam int copyId) {

        libraryService.deleteBookCopy(copyId);

        return new RedirectView("/library/{id}");
    }

    @RequestMapping("{id}/delete-title")
    RedirectView deleteTitle(@PathVariable("id") Integer bookId) {

        libraryService.deleteBookTitle(bookId);

        return new RedirectView("/library");
    }
    @RequestMapping("{id}/edit-book")
    RedirectView editBook(@PathVariable("id") Integer bookId, @ModelAttribute Book book) {

        libraryService.editBook(book);

        return new RedirectView("/library/{id}");
    }

}
