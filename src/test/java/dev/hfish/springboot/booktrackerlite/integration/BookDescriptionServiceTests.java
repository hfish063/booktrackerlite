package dev.hfish.springboot.booktrackerlite.integration;

import dev.hfish.springboot.booktrackerlite.service.description.BookDescriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDescriptionServiceTests {
    @Autowired
    BookDescriptionService bookDescriptionService;

    @Test
    public void findDescriptionBookDescriptionService() {

    }
}
