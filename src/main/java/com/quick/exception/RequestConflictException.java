package com.quick.exception;

public class RequestConflictException extends RuntimeException{

    public RequestConflictException(String message) {
        super(message);
    }

}
