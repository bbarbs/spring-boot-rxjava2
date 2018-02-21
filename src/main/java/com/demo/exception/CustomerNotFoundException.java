package com.demo.exception;

public class CustomerNotFoundException extends ApiException {

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String s) {
        super(s);
    }
}
