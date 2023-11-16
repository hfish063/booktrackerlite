package dev.hfish.springboot.booktrackerlite.service.description;

import dev.hfish.springboot.booktrackerlite.pojo.BookDescription;

public interface BookDescriptionService {
    public BookDescription findDescription(int bookId);
}
