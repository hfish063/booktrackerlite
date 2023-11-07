package dev.hfish.springboot.booktrackerlite.service;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookDescriptionServiceImpl {
    BookService bookService;
    RestTemplate restTemplate;

    @Autowired
    public BookDescriptionServiceImpl(BookService theBookService, RestTemplateBuilder theRestTemplateBuilder) {
        bookService = theBookService;
        restTemplate = theRestTemplateBuilder.build();
    }

    //TODO: generate a book description through open library api for book w/ corresponding id
    public BookDescription getDescription(int bookId) {
        BookDescription result = null;
        Book theBook = bookService.findById(bookId);
        String title = "";

        if (theBook != null) {
            // placeholder
        }

        // stub
        return result;
    }
}
