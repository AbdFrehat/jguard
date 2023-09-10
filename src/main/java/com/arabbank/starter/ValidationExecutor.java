package com.arabbank.starter;

import com.arabbank.validator.FileValidator;

public class ValidationExecutor {
    private final FileValidator fileValidator;

    public ValidationExecutor() {
        this.fileValidator = new FileValidator();
    }

    public void execute() {
        fileValidator.validate();
    }
}
