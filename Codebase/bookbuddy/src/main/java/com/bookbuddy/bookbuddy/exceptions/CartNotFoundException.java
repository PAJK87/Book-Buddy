package com.bookbuddy.bookbuddy.exceptions;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(Long cartId) {
        super("Could not find cart: " + cartId);
    }

}
