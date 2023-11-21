package dev.hfish.springboot.booktrackerlite.integration;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;
import dev.hfish.springboot.booktrackerlite.restclient.OpenLibraryClient;
import dev.hfish.springboot.booktrackerlite.service.description.BookDescriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(value = "/application-test.properties")
public class BookDescriptionServiceTests {
    @Autowired
    BookDescriptionService bookDescriptionService;

    @Mock
    OpenLibraryClient openLibraryClient;

    private Book testBookOne;

    @Value("${expectedDescription}")
    private String expectedBookDesc;

    @BeforeEach
    public void setUp() {
        testBookOne = new Book("Beautiful Code", "Oram & Wilson", 1, "");
    }

    @Test
    public void findDescriptionBookDescriptionService() {
        String theBookTitle = testBookOne.getTitle();

        when(openLibraryClient.getDescription(theBookTitle)).thenReturn(expectedBookDesc);

        assertSame(expectedBookDesc, openLibraryClient.getDescription(theBookTitle));

        BookDescription theBookDesc = bookDescriptionService.findDescription(theBookTitle);
        String actualBookDesc = theBookDesc.getDescription();

        assertEquals(expectedBookDesc, actualBookDesc);
    }
}
