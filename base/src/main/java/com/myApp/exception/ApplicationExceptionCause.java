package com.myApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public enum ApplicationExceptionCause implements Serializable {

    NOT_FOUND("not found", HttpStatus.BAD_REQUEST);

    private String code;
    private HttpStatus httpStatus;

    ApplicationExceptionCause(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

}