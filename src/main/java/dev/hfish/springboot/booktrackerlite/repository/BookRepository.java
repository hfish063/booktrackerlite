package dev.hfish.springboot.booktrackerlite.repository;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // provide author, and find a book with corresponding String
    //usage: used in controller testing, w/ mock http requests to verify successful post to db
    public Book findBookByAuthor(String theAuthor);
}
