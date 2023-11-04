package dev.hfish.springboot.booktrackerlite.pojo;

/**
 * BookDescription objects contain String w/ the description of specified book, fetched by the Open Library API
 * Object is added to model, and passed to view in order to render a description of user's book for them
 * IMPORTANT NOTE:
 * Description is not stored or cached in database, it is fetched every time a corresponding http request is called
 */
public class BookDescription {
    private String description;

    public BookDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BookDescription{" +
                "description='" + description + '\'' +
                '}';
    }
}
