package dev.hfish.springboot.booktrackerlite.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenLibraryClient {
    private RestTemplate restTemplate;

    @Autowired
    public OpenLibraryClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    /**
     * Returns String containing the text description of book w/ corresponding id
     * Makes two http requests to open library API, once to obtain the key of desired book, the second request
     * uses book's key to search for the description
     * usage: instantiate new BookDescription object, and use this method to set the 'description' field
     *
     * @param bookId id field of the book object we are finding a description for
     * @return String containing description text
     */
    public String getDescription(int bookId) {
        // TODO: implement getDescription client method
        // stub method
        return "Hello world!";
    }
}
