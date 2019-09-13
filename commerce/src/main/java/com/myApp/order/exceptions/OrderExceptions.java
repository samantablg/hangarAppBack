package com.myApp.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderExceptions {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class orderNotFound extends RuntimeException {

        public orderNotFound() {
            super("Order not found");
        }
    }
}
