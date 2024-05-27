package com.bookbuddy.bookbuddy.exceptions;

public class UserNotAuthorizedException extends RuntimeException {

    public UserNotAuthorizedException(Long id) {
        super("User cannot access this resource " + id);
    }
}
