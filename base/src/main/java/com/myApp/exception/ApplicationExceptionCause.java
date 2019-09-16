package com.myApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public enum ApplicationExceptionCause implements Serializable {

    NOT_FOUND( "not found" , HttpStatus.BAD_REQUEST),
    PRICE_CONFLICT("Conflict with price",HttpStatus.BAD_REQUEST),
    PRICE_CURRENT("Current price of product", HttpStatus.CREATED),
    USER_CONFLICT("User already exists", HttpStatus.CONFLICT),
    HANGAR_CONFLICT("Hangar already exists", HttpStatus.CONFLICT),
    PRODUCT_CONFLICT("Product already exists", HttpStatus.CONFLICT),
    NOT_STOCK("Not enough stock", HttpStatus.CONFLICT),
    PROD_HANG_UNLINK("Not exist any relationship", HttpStatus.NOT_FOUND),
    HANG_UNLINK("The hangar is not associated to any product", HttpStatus.NOT_FOUND),
    PROD_UNLINK("The product is not associated to any hangar", HttpStatus.NOT_FOUND);

    private String code;
    private HttpStatus httpStatus;

    ApplicationExceptionCause(String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
