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


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class hangarEmptyException extends RuntimeException {

        public hangarEmptyException() {
            super("Hangar can't be create");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class productEmptyException extends RuntimeException {

        public productEmptyException() {
            super("Product can't be create");
        }
    }
}
