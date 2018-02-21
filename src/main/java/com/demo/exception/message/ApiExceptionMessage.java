package com.demo.exception.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ApiExceptionMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date timestamp;

    @NotNull
    private int statusCode;

    @NotNull
    private HttpStatus httpStatus;

    private ErrorMessage error;

    public ApiExceptionMessage(Date timestamp, int statusCode, HttpStatus httpStatus, ErrorMessage error) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }
}
