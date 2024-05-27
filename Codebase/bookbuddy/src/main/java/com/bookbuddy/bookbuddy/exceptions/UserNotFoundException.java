package com.bookbuddy.bookbuddy.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }

    public UserNotFoundException(String string) {
        super(string);
    }

}
