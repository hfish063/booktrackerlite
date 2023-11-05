package dev.hfish.springboot.booktrackerlite.service;

import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;

public interface BookDescriptionService {
    public BookDescription getDescription(int bookId);
}
