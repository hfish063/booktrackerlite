package dev.hfish.springboot.booktrackerlite.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * IMPORTANT NOTE: the search functionality of client class relies on a correctly spelt book title, else we will not
 * receive an accurate response from open library API.
 *
 * @author haydenfish
 * @version 11.15.2023
 */

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
     * @param theBookId id field of the book object we are finding a description for
     * @return String containing description text
     */
    public String getDescription(int theBookId) {
        // TODO: implement getDescription client method
        // the key is a sequence of characters serving as an 'id' for finding book title through API
        // first step is to send http GET request and find key from response
        String key = null;

        // using the key, we can use the search functionality of API, and access specific information for a single book
        String description = "Hello World!";

        // return entire description of book
        return description;
    }
}
