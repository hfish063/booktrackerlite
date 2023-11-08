package dev.hfish.springboot.booktrackerlite.controller;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.entity.BookNote;
import dev.hfish.springboot.booktrackerlite.service.note.BookNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class BookNoteController {
    private BookNoteService bookNoteService;

    @Autowired
    public BookNoteController(BookNoteService theBookNoteService) {
        bookNoteService = theBookNoteService;
    }

    // mapping for GET "/showNoteForm" ... display the note-form view to user
    @GetMapping("/showNoteForm")
    public String showNoteForm(@RequestParam("bookId") Book theBookId, Model theModel) {
        BookNote theBookNote = bookNoteService.findBookNoteByBookId(theBookId);

        theModel.addAttribute("book_note", theBookNote);

        return "note-form";
    }

    // mapping for PUT "/save" ... update note with corresponding id in the database
    @PostMapping("/save")
    public String saveBookNote(@ModelAttribute("book_note") BookNote theBookNote) {
        bookNoteService.save(theBookNote);

        return "redirect:/books/home";
    }
}
