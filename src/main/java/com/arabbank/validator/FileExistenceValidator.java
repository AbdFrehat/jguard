package com.arabbank.validator;

import com.arabbank.exception.FileExistenceValidationException;
import com.arabbank.provider.FilesProvider;

import java.util.List;

public class FileExistenceValidator {

    private final FilesProvider filesProvider;
    private final String projectPersistPath;

    public FileExistenceValidator(FilesProvider filesProvider, String projectPersistPath) {
        this.filesProvider = filesProvider;
        this.projectPersistPath = projectPersistPath;
    }

    void validate(List<String> filesToValidate) throws FileExistenceValidationException {
        FileExistenceValidationException fileExistenceValidationException = new FileExistenceValidationException("File or more are not found!");
        filesToValidate.stream().filter(fileToValidate -> filesProvider
                .provide(fileToValidate).isEmpty())
                .forEach(fileToValidate -> fileExistenceValidationException
                        .addException(new FileExistenceValidationException(String.format("Unable to find the provided file: %s in %s", filesToValidate, projectPersistPath))));
        if (!fileExistenceValidationException.getExceptions().isEmpty()) {
            throw fileExistenceValidationException;
        }
    }
}
