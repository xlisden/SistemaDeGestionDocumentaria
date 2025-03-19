package com.unu.sistemadegestiondocumentaria.validations;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public void printConsoleMessage() {
        String message = getMessage();
        System.out.println(Validation.showWarning(message));
        StackTraceElement[] infoList = getStackTrace();
        for(StackTraceElement info: infoList){
            System.out.println(Validation.showWarning(info + ""));
        }
        System.out.println();
    }
    
    public void printMessage() {
        System.out.println(Validation.showWarning(getMessage()) + "\n");
    }
}
