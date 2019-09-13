package com.myApp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 2435263913277497680L;

    private HttpStatus httpStatus;
    private String message;
    private String clazz;

    public ApplicationException(ApplicationExceptionCause cause) {
        this.httpStatus = cause.getHttpStatus();
        this.message = cause.getCode();
        this.clazz = cause.getClass().getSimpleName();
    }

}
