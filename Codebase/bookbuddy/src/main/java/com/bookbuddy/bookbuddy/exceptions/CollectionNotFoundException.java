package com.bookbuddy.bookbuddy.exceptions;

public class CollectionNotFoundException extends RuntimeException {

    public CollectionNotFoundException(Long id) {
        super("Collection with id: " + id + " not found.");
    }

    public CollectionNotFoundException(Long userId, String name) {
        super("Collection with name: " + name + " not found for user with id: " + userId + ".");
    }
}
