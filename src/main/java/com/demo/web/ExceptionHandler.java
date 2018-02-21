package com.demo.web;

import com.demo.exception.CustomerNotFoundException;
import com.demo.exception.EmailExistsException;

public abstract class ExceptionHandler {

    /**
     * Handle exception related to customer.
     *
     * @param cause
     */
    protected void handleException(Throwable cause) {
        if(cause != null) {
            if (cause instanceof CustomerNotFoundException) {
                throw new CustomerNotFoundException(cause.getMessage());
            } else if (cause instanceof EmailExistsException) {
                throw new EmailExistsException(cause.getMessage());
            } else {
                throw new RuntimeException(cause.getMessage());
            }
        }
    }
}
