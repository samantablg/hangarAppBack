package com.myApp.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LoginExceptions {

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class userExistException extends RuntimeException {

        public userExistException() {
            super("User already exists");
        }
    }
}
