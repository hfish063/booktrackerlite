package dev.hfish.springboot.booktrackerlite.controller;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Book class, exposes all endpoints required to use application to user
 * All the controller methods return a html view
 *
 * @author haydenfish
 * @version 11.15.2023
 */

@Controller
@RequestMapping("/books")
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService theBookService) {
        bookService = theBookService;
    }

    // mapping for home page, "/" and "/home", will return a redirect if home path is not specified
    @GetMapping("/")
    public String index() {
        return "redirect:/books/home";
    }

    @GetMapping("/home")
    public String showHome(Model theModel) {
        List<Book> listOfAllBooks = bookService.findAll();

        theModel.addAttribute("books", listOfAllBooks);

        // stub for debugging
        // System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        return "home";
    }

    // mapping for POST "/save" ... save book to database
    // usage: for creating/updating database values using BookRepository save method,
    // Ex: 'Save book' button calls the "/save" mapping, and then saveBook method will redirect user after book is saved
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book theBook) {
        bookService.save(theBook);

        return "redirect:/books/home";
    }

    // mapping for DELETE "/delete" ... delete book by id from database
    // usage:
    @GetMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int theBookId) {
        bookService.deleteById(theBookId);

        return "redirect:/books/home";
    }

    // mapping for GET "/showBookForm" ... display the book-form view to user
    // usage: 'Add Book' button calls "/showBookForm" mapping, displaying the creation form for a new book
    @GetMapping("/showBookForm")
    public String showBookForm(Model theModel) {
        Book theBook = new Book();

        theModel.addAttribute("book", theBook);

        return "book-form";
    }

    // mapping for GET "/showNoteForm" ... display the note-form view to user
    // usage: 'Add/View Notes' button calls "/showNoteForm" mapping, displaying the note-form view to user
    @GetMapping("/showNoteForm")
    public String showNoteForm(@RequestParam("bookId") int theBookId, Model theModel) {
        Book theBook = bookService.findById(theBookId);

        theModel.addAttribute("book", theBook);

        return "note-form";
    }

    // mapping for GET "/showUpdateForm" ... display the update-form view to user
    // usage: 'Update' button calls "/showNoteForm" mapping, with id of book to update as parameter, displaying book-form
    // view with current book's fields as form values to user
    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("bookId") int theBookId, Model theModel) {
        Book theBook = bookService.findById(theBookId);

        theModel.addAttribute("book", theBook);

        return "book-form";
    }
}
