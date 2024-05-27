package com.bookbuddy.bookbuddy.exceptions;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long genreId) {
        super("Could not find genre: " + genreId);
    }
}
