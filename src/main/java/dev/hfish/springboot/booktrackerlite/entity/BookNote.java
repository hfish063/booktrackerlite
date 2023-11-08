package dev.hfish.springboot.booktrackerlite.entity;

import jakarta.persistence.*;

/**
 * BookNote entity maps user entered text related to specific book to database, notes are created/viewed through the
 * "/bookNote" endpoint
 * The bookId field denotes the book (from "book" table) that this note "belongs" to
 */

@Entity
@Table(name = "note")
public class BookNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book bookId;

    public BookNote() {

    }

    public BookNote(Book theBookId) {
        bookId = theBookId;
    }

    @Column(name = "body")
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String text) {
        this.body = text;
    }

    @Override
    public String toString() {
        return "BookNote{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", body='" + body + '\'' +
                '}';
    }
}
