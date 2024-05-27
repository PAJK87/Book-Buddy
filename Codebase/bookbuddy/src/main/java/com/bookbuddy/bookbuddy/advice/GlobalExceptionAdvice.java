package com.bookbuddy.bookbuddy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bookbuddy.bookbuddy.exceptions.BookAlreadyInCollectionException;
import com.bookbuddy.bookbuddy.exceptions.BookNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.CartItemNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.CartNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.CollectionNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.GenreNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.OrderNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CollectionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String collectionNotFoundHandler(CollectionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BookAlreadyInCollectionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookAlreadyInCollectionHandler(BookAlreadyInCollectionException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CartItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cartItemNotFoundHandler(CartItemNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cartNotFoundHandler(CartNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(GenreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String genreNotFoundHandler(GenreNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderNotFoundHandler(OrderNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BookAlreadyInCollectionException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookAlreadyInCollectionHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

}