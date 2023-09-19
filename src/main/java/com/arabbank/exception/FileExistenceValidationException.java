package com.arabbank.exception;

import java.util.ArrayList;
import java.util.List;

public class FileExistenceValidationException extends Exception {

    private List<Throwable> exceptions = new ArrayList<>();


    public FileExistenceValidationException(String message) {
        super(message);
    }

        public void addException(Throwable throwable) {
        exceptions.add(throwable);
    }

    public List<Throwable> getExceptions() {
        return exceptions;
    }
    
}
