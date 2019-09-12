package com.myApp.product_hangar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Product_HangarException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class Product_HangarNotExistException extends RuntimeException {

        public Product_HangarNotExistException() {
            super("Not exist any relationship");
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ProductNotAssociatedException extends RuntimeException {

        public ProductNotAssociatedException(long id) {
            super(String.format("The product %d is not associated to any hangar",id));
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class HangarNotAssociatedException extends RuntimeException {

        public HangarNotAssociatedException(long id) {
            super(String.format("The hangar %d is not associated to any product",id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class ProductAndHangarNotAssociatedException extends RuntimeException {

        public ProductAndHangarNotAssociatedException() {
            super("The product is not in the hangar");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class StockException extends RuntimeException {

        public StockException() {
            super("Not enough stock");
        }
    }

}
