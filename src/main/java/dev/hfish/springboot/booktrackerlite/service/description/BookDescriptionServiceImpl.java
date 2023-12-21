package dev.hfish.springboot.booktrackerlite.service.description;

import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;
import dev.hfish.springboot.booktrackerlite.restclient.OpenLibraryClient;
import dev.hfish.springboot.booktrackerlite.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDescriptionServiceImpl implements BookDescriptionService {
    private final String descriptionNotFoundResponse = "Unable to locate a description for this title.";

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
     * @param theBookTitle title of the book we are finding description for, spelling errors are not valid
     * @return BookDescription object, description field contains our findings
     */
    public BookDescription findDescription(String theBookTitle) {
        String getDescriptionText = openLibraryClient.getDescription(theBookTitle);

        if (getDescriptionText == null) {
            return new BookDescription(descriptionNotFoundResponse);
        }

        return new BookDescription(getDescriptionText);
    }
}
