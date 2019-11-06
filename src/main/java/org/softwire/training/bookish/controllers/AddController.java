package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BookPageModel;
import org.softwire.training.bookish.models.page.LibraryPageModel;
import org.softwire.training.bookish.services.AddService;
import org.softwire.training.bookish.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@Controller
@RequestMapping("/add")
public class AddController {
    private final AddService addService;

    @RequestMapping("")
    ModelAndView add() {
        return new ModelAndView("add");
    }

    @Autowired
    public AddController(AddService addService) {
        this.addService = addService;
    }


    @RequestMapping("/add-title")
    RedirectView addNewTitle(@ModelAttribute Book book) {

        addService.addNewTitle(book);

        return new RedirectView("/library");
    }
}
