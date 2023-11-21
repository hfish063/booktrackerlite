package dev.hfish.springboot.booktrackerlite.controller;

import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;
import dev.hfish.springboot.booktrackerlite.service.description.BookDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/description")
public class BookDescriptionController {
    private BookDescriptionService bookDescriptionService;

    @Autowired
    public BookDescriptionController(BookDescriptionService theBookDescriptionService) {
        bookDescriptionService = theBookDescriptionService;
    }

    // mapping for GET "/showBookDescription" ... display description of book, obtained from http request to API
    // usage:
    @GetMapping("/showBookDescription")
    public String showBookDescription(@RequestParam("bookTitle") String theBookTitle, Model theModel) {
        BookDescription theBookDescription = bookDescriptionService.findDescription(theBookTitle);

        theModel.addAttribute("book_description", theBookDescription);
        theModel.addAttribute("book_title", theBookTitle);

        return "book-description";
    }
}
