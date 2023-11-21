package dev.hfish.springboot.booktrackerlite.restclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;

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
    private ClientHelper clientHelper;
    private ObjectMapper objectMapper;

    @Autowired
    public OpenLibraryClient(RestTemplateBuilder restTemplateBuilder, ClientHelper theClientHelper,
                             ObjectMapper theObjectMapper) {
        restTemplate = restTemplateBuilder.build();
        clientHelper = theClientHelper;
        objectMapper = theObjectMapper;
    }

    /**
     * Returns String containing the text description of book w/ corresponding id
     * Makes two http requests to open library API, once to obtain the key of desired book, the second request
     * uses book's key to search for the description
     * usage: instantiate new BookDescription object, and use this method to set the 'description' field
     *
     * @param theBookTitle title field of the book we are finding desc. for, spelling errors are not valid
     * @return String containing description text, null if not found
     */
    // TODO: getDescription() method
    public String getDescription(String theBookTitle) {
        // the key is a sequence of characters serving as an 'id' for finding book title through API
        String key = null;
        String description = null;

        key = clientHelper.findBookKey(theBookTitle);

        String uri = "https://openlibrary.org" + key + ".json"; // .json must be used to get json format response

        ResponseEntity<String> apiResponse = restTemplate.getForEntity(uri, String.class);
        String jsonString = String.valueOf(apiResponse);
        jsonString = clientHelper.removeHttpStatus(jsonString);

        return parseJsonForDescription(jsonString);
    }

    private String parseJsonForDescription(String theJsonString) {
        JsonNode rootNode = null;
        JsonNode descriptionNode = findDescriptionJsonNode(rootNode, new StringReader(theJsonString));

        if (descriptionNode == null) {
            return null;
        }

        // stub
        return descriptionNode.asText();
    }

    private JsonNode findDescriptionJsonNode(JsonNode theRootNode, StringReader theJsonStringReader) {
        try {
            theRootNode = objectMapper.readTree(theJsonStringReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode descriptionNode = theRootNode.get("description");

        // if description is not found, check 'value' attribute
        if (descriptionNode != null && descriptionNode.asText().isBlank()) {
            return descriptionNode.get("value");
        }

        return descriptionNode;
    }
}
