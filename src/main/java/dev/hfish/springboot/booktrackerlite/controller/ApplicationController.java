package dev.hfish.springboot.booktrackerlite.controller;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: Application docs
 */

@Controller
@RequestMapping("/books")
public class ApplicationController {
    BookService bookService;

    @Autowired
    public ApplicationController(BookService theBookService) {
        bookService = theBookService;
    }

    // mapping for GET "/" ... redirects to home page
    @GetMapping("/")
    public String index() {
        return "redirect:/books/home";
    }

    // mapping for GET "/home" ... displays the home page
    @GetMapping("/home")
    public String showHome(Model theModel) {
        List<Book> theBooks = bookService.findAll();

        theModel.addAttribute("books", theBooks);

        return "home";
    }

    // mapping for POST "/save" ... save book to database
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book theBook) {
        bookService.save(theBook);

        return "redirect:/books/home";
    }

    // mapping for GET "/showBookForm" ... display the book-form view to user
    @GetMapping("/showBookForm")
    public String showBookForm(Model theModel) {
        Book theBook = new Book();

        theModel.addAttribute("book", theBook);

        return "book-form";
    }

    // mapping for DELETE "/delete" ... delete book by id from database
    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int theBookId) {
        bookService.deleteById(theBookId);

        return "redirect:/books/home";
    }
}
