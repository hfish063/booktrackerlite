package dev.hfish.springboot.booktrackerlite.service.book;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.entity.BookNote;
import dev.hfish.springboot.booktrackerlite.repository.BookNoteRepository;
import dev.hfish.springboot.booktrackerlite.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookNoteRepository bookNoteRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository, BookNoteRepository theBookNoteRepository) {
        bookRepository = theBookRepository;
        bookNoteRepository = theBookNoteRepository;
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
     * RESPONSIBILITY: save corresponding BookNote to database as well
     *
     * @param theBook user provided book to save in database
     * @return the book we are saving
     */
    @Override
    public Book save(Book theBook) {
        // save corresponding book note
        BookNote bookNote = new BookNote(theBook);
        bookNoteRepository.save(bookNote);

        return bookRepository.save(theBook);
    }

    /**
     * Delete a book object with specified id from book_catalog database
     * RESPONSIBILITY: delete the corresponding BookNote as well
     * TODO: implement the deleteByBookId method in BookNoteService
     *
     * @param theId id of the book we are deleting
     */
    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }
}
