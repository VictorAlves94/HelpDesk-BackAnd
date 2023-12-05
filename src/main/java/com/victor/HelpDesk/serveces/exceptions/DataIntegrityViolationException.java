package com.victor.HelpDesk.serveces.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
    private static final long serialVersionUid = 1L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
