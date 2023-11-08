package dev.hfish.springboot.booktrackerlite.service.note;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.entity.BookNote;

public interface BookNoteService {
    public BookNote findBookNoteByBookId(Book theBookId);

    public BookNote save(BookNote theBookNote);

    public void deleteById(int theId);

    public void deleteBookNoteByBookId(Book theBookId);
}
