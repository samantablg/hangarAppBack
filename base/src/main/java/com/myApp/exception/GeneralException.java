package com.myApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GeneralException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class HangarNotFoundException extends RuntimeException {

        public HangarNotFoundException() {
            super("There is not hangar");
        }

        public HangarNotFoundException(Long id) {
            super(String.format("The hangar %d doesn't exist", id));
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class HangarExistException extends RuntimeException {

        public HangarExistException() { super("Hangar already exist"); }

        public HangarExistException(Long id) {
            super(String.format("The hangar %d already exist", id));
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFound extends RuntimeException {

        public NotFound() {
            super("There are no products in the database");
        }

        public NotFound(Long id) {
            super(String.format("The product %d doesn't exist", id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class ProductEmptyException extends RuntimeException {

        public ProductEmptyException() {
            super("Product without info");
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class ProductExistException extends RuntimeException {

        public ProductExistException() {
            super("Product already exist");
        }
    }


}
