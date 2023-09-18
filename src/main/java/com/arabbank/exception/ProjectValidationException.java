package com.arabbank.exception;

import java.util.ArrayList;
import java.util.List;

public class ProjectValidationException extends RuntimeException {

    private List<Throwable> exceptions = new ArrayList<>();

    public ProjectValidationException(String message) {
        super(message);
    }

    public void addException(Throwable throwable) {
        exceptions.add(throwable);
    }

    public List<Throwable> getExceptions() {
        return exceptions;
    }
    
}
