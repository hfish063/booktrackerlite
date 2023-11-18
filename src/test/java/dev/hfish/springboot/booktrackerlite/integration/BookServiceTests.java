package dev.hfish.springboot.booktrackerlite.integration;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.repository.BookRepository;
import dev.hfish.springboot.booktrackerlite.service.book.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class BookServiceTests {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUpDatabase() {
        jdbcTemplate.execute("insert into book(id, title, author, page, note) " +
                "values (10, 'Title', 'One', 1, '')"
        );
    }

    @AfterEach
    public void cleanUpDatabase() {
        jdbcTemplate.execute("delete from book");
    }

    @Test
    public void findAllBookService() {
        Iterable<Book> iterableBooks = bookService.findAll();

        List<Book> bookList = new ArrayList<>();

        for(Book currentBook : iterableBooks) {
            bookList.add(currentBook);
        }

        assertEquals(1, bookList.size());
    }

    @Test
    public void findByIdBookService() {
        Book foundBook = bookService.findById(10);

        assertEquals(foundBook.getTitle(), "Title");
        assertEquals(foundBook.getAuthor(), "One");
        assertEquals(foundBook.getPage(), 1);
        assertSame(foundBook.getNote(), "");

        assertThrows(RuntimeException.class, () -> bookService.findById(0), "should throw exception");
    }

    @Test
    public void saveBookService() {
        Book theBook = new Book("Title", "Zero", 1, "");

        assertDoesNotThrow(() -> bookService.save(theBook));
    }

    @Test
    public void deleteBookService() {
        Optional<Book> deletedBook = bookRepository.findById(10);

        assertTrue(deletedBook.isPresent());

        bookService.deleteById(10);
        deletedBook = bookRepository.findById(10);

        assertFalse(deletedBook.isPresent());
    }
}
