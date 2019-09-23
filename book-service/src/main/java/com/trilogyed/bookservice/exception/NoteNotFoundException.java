package com.trilogyed.bookservice.exception;

public class NoteNotFoundException extends IllegalArgumentException {
    public NoteNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
