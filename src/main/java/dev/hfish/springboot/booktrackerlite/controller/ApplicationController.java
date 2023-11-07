package dev.hfish.springboot.booktrackerlite.controller;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ApplicationController {
    BookService bookService;

    @Autowired
    public ApplicationController(BookService theBookService) {
        bookService = theBookService;
    }

    // mapping for GET "/" ... redirects to home page
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    // mapping for GET "/home" ... displays the home page
    @GetMapping("/home")
    public String showHome(Model theModel) {
        List<Book> theBooks = bookService.findAll();

        theModel.addAttribute("books", theBooks);

        return "home";
    }
}
