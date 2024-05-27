package com.bookbuddy.bookbuddy.exceptions;

public class CartItemNotFoundException extends RuntimeException {

    public CartItemNotFoundException(Long cartItemId) {
        super("Could not find cart item: " + cartItemId);
    }
}
