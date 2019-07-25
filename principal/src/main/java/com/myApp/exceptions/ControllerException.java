package com.myApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ControllerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class idNotAllowed extends RuntimeException {

        public idNotAllowed(long id) {
            super(String.format("id: %d  is not allowed", id));
        }
    }
}
