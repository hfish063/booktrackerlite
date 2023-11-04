package dev.hfish.springboot.booktrackerlite.service;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import dev.hfish.springboot.booktrackerlite.repository.BookRepository;

import java.util.List;

public interface BookService {
    public List<Book> findAll();

    public Book findById(int theId);

    public Book save(Book theBook);

    public void deleteById(int theId);
}
