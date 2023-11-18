package dev.hfish.springboot.booktrackerlite.service.description;

import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;
import dev.hfish.springboot.booktrackerlite.restclient.OpenLibraryClient;
import dev.hfish.springboot.booktrackerlite.service.book.BookService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookDescriptionServiceImpl implements BookDescriptionService {
    private final String resourceUrl = "https://openlibrary.org";

    BookService bookService;
    OpenLibraryClient openLibraryClient;

    @Autowired
    public BookDescriptionServiceImpl(BookService theBookService, OpenLibraryClient theOpenLibraryClient) {
        bookService = theBookService;
        openLibraryClient = theOpenLibraryClient;
    }

    // TODO: generate a book description through open library api for book w/ corresponding id

    /**
     * Returns a book description with corresponding description text from API call
     * Responsibilities include formatting the title of book we are searching for to adhere to API url format,
     * then sending an initial GET request to find our title's key in library database,
     * when we have the key then we are able to search for a single work, and get its description
     *
     * @param theBookId id of the book we are finding description for, should be primary key of integer value
     * @return BookDescription object, description field contains our findings
     */
    public BookDescription findDescription(int theBookId) {
        String getDescriptionText = openLibraryClient.getDescription(theBookId);

        return new BookDescription(getDescriptionText);
    }
}
