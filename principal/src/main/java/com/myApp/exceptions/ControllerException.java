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
    public static class searchHangarException extends RuntimeException {
        public searchHangarException() {
            super("Write the name of hangar");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class searchProductException extends RuntimeException {
        public searchProductException() {
            super("Write the name of product");
        }
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public static class productEmptyException extends RuntimeException {
        public productEmptyException() {
            super("Product can't be create");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class hangarExistException extends RuntimeException {
        public hangarExistException() {
            super("Hangar already exist");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class productExistException extends RuntimeException {
        public productExistException() {
            super("Product already exist");
        }
    }
}
