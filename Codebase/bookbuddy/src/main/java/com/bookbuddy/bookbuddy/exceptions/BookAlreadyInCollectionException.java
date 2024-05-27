package com.bookbuddy.bookbuddy.exceptions;

public class BookAlreadyInCollectionException extends RuntimeException {
    public BookAlreadyInCollectionException(String title) {
        super("Book: " + title + " already in collection");
    }
}
