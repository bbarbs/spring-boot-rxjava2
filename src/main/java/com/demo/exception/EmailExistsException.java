package com.demo.exception;

public class EmailExistsException extends ApiException {

    public EmailExistsException() {
    }

    public EmailExistsException(String s) {
        super(s);
    }
}
