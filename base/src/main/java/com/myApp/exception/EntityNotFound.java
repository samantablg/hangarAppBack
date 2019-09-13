package com.myApp.exception;

import javax.persistence.EntityNotFoundException;

public class EntityNotFound extends RuntimeException {

    private static final long serialVersionUID = 5067100674895732114L;

    public EntityNotFound(Class clazz) {
        super(clazz.getSimpleName() + " was not found");
    }


}
