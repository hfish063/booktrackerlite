package dev.hfish.springboot.booktrackerlite.repository;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.entity.BookNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookNoteRepository extends JpaRepository<BookNote, Integer> {
    public BookNote findBookNoteByBookId(Book theBookId);

    public void deleteBookNoteByBookId(Book theBookId);
}
