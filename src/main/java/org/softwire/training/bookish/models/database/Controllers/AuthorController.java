package org.softwire.training.bookish.models.database.Controllers;

import org.softwire.training.bookish.models.database.Models.Author;
import org.softwire.training.bookish.models.database.Models.BookModel;
import org.softwire.training.bookish.models.database.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = "/getBooks", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public List<BookModel> getAuthorBooks(@ModelAttribute Author author){
        List<BookModel> books = authorService.getAuthorBooks(author.getAuthor());
        return books;
    }
}
