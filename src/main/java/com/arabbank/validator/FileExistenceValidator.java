package com.arabbank.validator;

import java.util.List;

import com.arabbank.exception.FileExistenceValidationException;
import com.arabbank.provider.FilesProvider;

public class FileExistenceValidator {

    private FilesProvider filesProvider;
    private String projectPersistPath;

    public FileExistenceValidator(FilesProvider filesProvider, String projectPersistPath) {
        this.filesProvider = filesProvider;
        this.projectPersistPath = projectPersistPath;
    }

    void validate(List<String> filesToValidate) throws FileExistenceValidationException {
        FileExistenceValidationException fileExistenceValidationException = new FileExistenceValidationException(
                "File or more are not found!");
        for (String fileToValidate : filesToValidate) {
            if (filesProvider.provide(fileToValidate).isEmpty()) {
                fileExistenceValidationException.addException(
                        new FileExistenceValidationException(String.format("Unable to find the provided file: %s in %s",
                                filesToValidate, projectPersistPath)));
            }
        }
        if (!fileExistenceValidationException.getExceptions().isEmpty()) {
            throw fileExistenceValidationException;
        }
    }

}
