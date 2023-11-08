package dev.hfish.springboot.booktrackerlite.service.note;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.entity.BookNote;
import dev.hfish.springboot.booktrackerlite.repository.BookNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookNoteServiceImpl implements BookNoteService{
    private BookNoteRepository bookNoteRepository;

    @Autowired
    public BookNoteServiceImpl(BookNoteRepository theBookNoteRepository) {
        bookNoteRepository = theBookNoteRepository;
    }

    /**
     * Find the BookNote from database table with corresponding bookId
     *
     * @param theBookId id of book this note "belongs" to
     * @return BookNote with specified bookId
     */
    public BookNote findBookNoteByBookId(Book theBookId) {
        BookNote result = null;

        Optional<BookNote> theBookNote = Optional.ofNullable(bookNoteRepository.findBookNoteByBookId(theBookId));

        if (theBookNote.isPresent()) {
            result = theBookNote.get();
        } else {
            throw new RuntimeException("Could note locate book note with bookId - " + theBookId);
        }

        return result;
    }

    /**
     * Save BookNote entity to database table "note"
     *
     * @param theBookNote the BookNote object/entity we want to save to database table
     * @return the entity that has been saved to table
     */
    public BookNote save(BookNote theBookNote) {
        return bookNoteRepository.save(theBookNote);
    }

    /**
     * Delete BookNote entity from database with corresponding id
     *
     * @param theId id of the BookNote entity we wish to delete from table
     */
    public void deleteById(int theId) {
        bookNoteRepository.deleteById(theId);
    }

    /**
     * Delete BookNote entity from database with corresponding *bookId*
     *
     * @param theBookId bookId of the BookNote entity to delete
     */
    @Override
    public void deleteBookNoteByBookId(Book theBookId) {
        bookNoteRepository.deleteBookNoteByBookId(theBookId);
    }
}
