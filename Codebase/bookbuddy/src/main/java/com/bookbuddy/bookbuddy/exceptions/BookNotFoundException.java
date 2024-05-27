package com.bookbuddy.bookbuddy.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long bookId) {
        super("Could not find book " + bookId);
    }

}
