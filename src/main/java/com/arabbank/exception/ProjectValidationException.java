package com.arabbank.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProjectValidationException extends RuntimeException {

    private final List<Throwable> exceptions = new ArrayList<>();

    public ProjectValidationException(String message) {
        super(message);
    }

    public void addException(Throwable throwable) {
        exceptions.add(throwable);
    }

}
