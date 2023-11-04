package dev.hfish.springboot.booktrackerlite.service;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository) {
        bookRepository = theBookRepository;
    }

    /**
     * Return list of all books in catalog
     *
     * @return list of every book in the database
     */
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * Find a book by the id, throw exception if we are unable to locate a book w/ corresponding id
     *
     * @param theId id of the book to search catalog for
     * @return book object with specified id, null if not found
     */
    @Override
    public Book findById(int theId) {
        Optional<Book> theBook = bookRepository.findById(theId);
        Book result = null;

        if (theBook.isPresent()) {
            result = theBook.get();
        } else {
            throw new RuntimeException("Could note locate book with id - " + theId);
        }

        return result;
    }

    /**
     * Save book object to book_catalog database
     *
     * @param theBook user provided book to save in database
     * @return the book we are saving
     */
    @Override
    public Book save(Book theBook) {
        return bookRepository.save(theBook);
    }

    /**
     * Delete a book object with specified id from book_catalog database
     *
     * @param theId id of the book we are deleting
     */
    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }
}
