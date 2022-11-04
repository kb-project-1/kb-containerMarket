package com.kb1.containerMarket.exception;

public class CustomInternalServerErrorException extends RuntimeException {

    public CustomInternalServerErrorException(String message) {
        super(message);
    }
}