package com.demo.exception.handler;


import com.demo.exception.ApiException;
import com.demo.exception.CustomerNotFoundException;
import com.demo.exception.EmailExistsException;
import com.demo.exception.message.ApiExceptionMessage;
import com.demo.exception.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Error response for {@link CustomerNotFoundException}
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> customerNotFoundException(ApiException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                new ErrorMessage(e.getMessage())
        ), HttpStatus.NOT_FOUND);
    }

    /**
     * Error response for {@link EmailExistsException}
     *
     * @param e
     * @return
     */
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ApiExceptionMessage> customerExistsException(ApiException e) {
        return new ResponseEntity<>(new ApiExceptionMessage(
                new Date(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT,
                new ErrorMessage(e.getMessage())
        ), HttpStatus.CONFLICT);
    }
}
