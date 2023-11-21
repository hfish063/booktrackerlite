package dev.hfish.springboot.booktrackerlite.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;

@Component
public class ClientHelper {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public ClientHelper(RestTemplateBuilder restTemplateBuilder, ObjectMapper theObjectMapper) {
        restTemplate = restTemplateBuilder.build();
        objectMapper = theObjectMapper;
    }

    public String findBookKey(String theBookTitle) {
        String key = null;
        if (theBookTitle.isBlank()) {
            return null;
        }

        theBookTitle = formatBookTitle(theBookTitle);

        String uri = "https://openlibrary.org/search.json?title=" + theBookTitle;
        ResponseEntity<String> apiResponse = restTemplate.getForEntity(uri, String.class);

        String jsonString = String.valueOf(apiResponse);
        jsonString = removeHttpStatus(jsonString);

        //return jsonString;
        return parseJsonForKey(jsonString);
    }

    private String parseJsonForKey(String theJsonString) {
        //String jsonKey = null;
        JsonNode rootNode = null;

        JsonNode keyNode = findKeyJsonNode(rootNode, new StringReader(theJsonString));

        if (keyNode == null) {
            return null;
        }

        return keyNode.asText();
    }

    private JsonNode findKeyJsonNode(JsonNode theRootNode, StringReader theJsonStringReader) {

        try {
            theRootNode = objectMapper.readTree(theJsonStringReader);
        } catch (IOException e) {
            e.printStackTrace();
            // return jsonKey;
        }

        JsonNode innerNode = theRootNode.get("docs");

        return innerNode.get(0).get("key");
    }

    private String formatBookTitle(String theBookTitle) {
        return theBookTitle.replace(" ", "+");
    }

    /**
     * Unable to parse Json String w/ jackson if there is a http status response preceding Json body,
     * use this method to remove the http response, enabling parsing
     *
     * @param theJsonString must be a non-empty Json String, beginning with http status
     * @return remove http status from Json String, and return
     */
    public String removeHttpStatus(String theJsonString) {
        int currIndex = 0;
        char[] temp = theJsonString.toCharArray();

        while (temp[currIndex] != '{') {
            temp[currIndex] = ' ';
            currIndex++;
        }

        return String.valueOf(temp);
    }
}
