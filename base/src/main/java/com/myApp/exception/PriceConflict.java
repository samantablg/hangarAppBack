package com.myApp.exception;

public class PriceConflict extends RuntimeException {

    private static final long serialVersionUID = 5067100674895732123L;

    public PriceConflict(Class clazz) {
        super(clazz.getSimpleName() + " incorrect");
    }
}
