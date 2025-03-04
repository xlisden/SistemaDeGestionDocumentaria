package com.unu.sistemadegestiondocumentaria.validations;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public void printMessage() {
        String message = getMessage();
        System.out.println(Validation.showWarning(message));
        StackTraceElement[] info = getStackTrace();
        System.out.println(Validation.showWarning(info[0]+""));
        System.out.println();
    }

}
