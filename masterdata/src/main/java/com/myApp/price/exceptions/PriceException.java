package com.myApp.price.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PriceException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class PriceNotFoundException extends RuntimeException {

        public PriceNotFoundException() {
            super("There is not com.myApp.price");
        }

        public PriceNotFoundException(Long id) {
            super(String.format("The com.myApp.price %d doesn't exist", id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class PriceExistException extends RuntimeException {

        public PriceExistException() {
            super("Product and com.myApp.price already exist");
        }
    }

}
