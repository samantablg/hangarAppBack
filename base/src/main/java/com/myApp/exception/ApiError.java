package com.myApp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ApiError {

    private HttpStatus httpStatus;

    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    ApiError(HttpStatus httpStatus) {
        this();
        this.httpStatus = httpStatus;
    }

    ApiError(HttpStatus httpStatus, Throwable exception) {
        this();
        this.httpStatus = httpStatus;
        this.message = "Unexpected error";
        this.debugMessage = exception.getLocalizedMessage();
    }

    ApiError(HttpStatus httpStatus, String message, Throwable exception) {
        this();
        this.httpStatus = httpStatus;
        this.message = message;
        this.debugMessage = exception.getLocalizedMessage();
    }

    public void addValidationError(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addSubError(ApiSubError subError) {
        if(subError != null)
            subErrors.add(subError);
    }

    private void addValidationError(FieldError fieldError) {
        ApiValidationError validationError = new ApiValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage()
                );
        addSubError(validationError);
    }
}
