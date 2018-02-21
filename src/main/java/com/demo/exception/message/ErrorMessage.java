package com.demo.exception.message;

import javax.validation.constraints.NotNull;

public class ErrorMessage {

    @NotNull
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
