package com.bookbuddy.bookbuddy.exceptions;

public class BookAlreadyInCollectionException extends RuntimeException {
    public BookAlreadyInCollectionException() {
        super("Book already in collection");
    }
}
