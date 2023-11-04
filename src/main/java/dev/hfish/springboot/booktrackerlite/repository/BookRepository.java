package dev.hfish.springboot.booktrackerlite.repository;

import dev.hfish.springboot.booktrackerlite.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
