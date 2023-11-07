package dev.hfish.springboot.booktrackerlite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * BookNote entity maps user entered text related to specific book to database, notes are created/viewed through the
 * "/bookNote" endpoint
 * The bookId field denotes the book (from book_catalog table) that this note "belongs" to
 */

// @Entity
// @Table(name = "book_notes")
public class BookNote {
    private int id;
    private int bookId;
    private String text;

    public BookNote(int id, int bookId, String text) {
        this.id = id;
        this.bookId = bookId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "BookNote{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", text='" + text + '\'' +
                '}';
    }
}
