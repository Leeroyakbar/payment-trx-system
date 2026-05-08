package com.avows.product.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        this("Data not found");
    }


    public NotFoundException(String message) {
        super(message);
    }
}
