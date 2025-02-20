package com.unu.sistemadegestiondocumentaria.validations;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public void printMessage() {
        System.out.println(Validation.showWarning(getMessage()));
    }

}
