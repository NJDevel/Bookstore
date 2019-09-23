package com.trilogyed.bookservice.exception;

public class BookNotFoundException extends IllegalArgumentException {
    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
