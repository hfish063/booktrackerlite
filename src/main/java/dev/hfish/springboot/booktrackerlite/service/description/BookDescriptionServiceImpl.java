package dev.hfish.springboot.booktrackerlite.service.description;

import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;
import dev.hfish.springboot.booktrackerlite.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookDescriptionServiceImpl implements BookDescriptionService {
    private final String resourceUrl = "https://openlibrary.org";

    BookService bookService;
    RestTemplate restTemplate;

    @Autowired
    public BookDescriptionServiceImpl(BookService theBookService, RestTemplateBuilder theRestTemplateBuilder) {
        bookService = theBookService;
        restTemplate = theRestTemplateBuilder.build();
    }

    //TODO: generate a book description through open library api for book w/ corresponding id

    /**
     * Returns a book description with corresponding description text from API call
     * Responsibilities include formatting the title of book we are searching for to adhere to API url format,
     * then sending an initial GET request to find our title's key in library database,
     * when we have the key then we are able to search for a single work, and get its description
     *
     * @param bookId id of the book we are finding description for
     * @return BookDescription object, description field contains our findings
     */
    public BookDescription getDescription(int bookId) {
        return new BookDescription("Demo response for bookId - " + bookId);
    }
}
