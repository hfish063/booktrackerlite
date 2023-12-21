package dev.hfish.springboot.booktrackerlite.integration;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.repository.BookRepository;
import dev.hfish.springboot.booktrackerlite.service.book.BookService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(value = "/application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {
    static MockHttpServletRequest mockHttpServletRequest;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookRepository bookRepository;

    @Mock
    BookService bookService;

    @BeforeAll
    public static void setUp() {
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setParameter("title", "Think java");
        mockHttpServletRequest.setParameter("author", "Mayfield");
        mockHttpServletRequest.setParameter("page", "1");
        mockHttpServletRequest.setParameter("note", "");
    }

    @AfterEach
    public void cleanUpDatabase() {
        jdbcTemplate.execute("delete from book");
    }

    @Test
    public void showHomeHttpRequest() throws Exception {
        Book testBookOne = new Book("Think Java", "Mayfield", 1, "");
        Book testBookTwo = new Book("1984", "George Orwell", 1, "");

        List<Book> bookList = new ArrayList<>(Arrays.asList(testBookOne, testBookTwo));

        when(bookService.findAll()).thenReturn(bookList);

        assertIterableEquals(bookList, bookService.findAll());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books/home"))
                .andExpect(status().isOk()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "home");
    }

    @Test
    public void saveHttpRequest() throws Exception {
        Book testBookOne = new Book("Think Java", "Mayfield", 1, "");

        List<Book> bookList = new ArrayList<>(Arrays.asList(testBookOne));

        when(bookService.findAll()).thenReturn(bookList);

        assertIterableEquals(bookList, bookService.findAll());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("title", mockHttpServletRequest.getParameterValues("title"))
                        .param("author", mockHttpServletRequest.getParameterValues("author"))
                        .param("page", mockHttpServletRequest.getParameterValues("page"))
                        .param("note", mockHttpServletRequest.getParameterValues("note")))
                .andExpect(status().isFound()).andReturn();

        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "redirect:/books/home");

        Book verifyBook = bookRepository.findBookByAuthor("Mayfield");

        assertNotNull(verifyBook, "if book is found, it should not be null");
    }
}
