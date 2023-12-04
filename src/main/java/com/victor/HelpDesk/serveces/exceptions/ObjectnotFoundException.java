package com.victor.HelpDesk.serveces.exceptions;

public class ObjectnotFoundException extends RuntimeException{
    private static final long serialVersionUid = 1L;

    public ObjectnotFoundException(String message) {
        super(message);
    }

    public ObjectnotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
