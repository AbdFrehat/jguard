package com.arabbank.validator;

import com.arabbank.exception.ResourceBundleValidationException;
import com.arabbank.provider.FilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceBundleValidator {

    private static final Logger logger = LoggerFactory.getLogger(ResourceBundleValidator.class);
    private final FilesProvider filesProvider;
    private final String projectPersistPath;
    private static final Integer NUMBER_OF_RESOURCE_BUNDLES_SECTION = 5;

    public ResourceBundleValidator(FilesProvider filesProvider, String projectPersistPath) {
        this.filesProvider = filesProvider;
        this.projectPersistPath = projectPersistPath;
    }

    void validate() throws ResourceBundleValidationException {
        ResourceBundleValidationException resourceBundleValidationException = new ResourceBundleValidationException("One or more exceptions are not linked to resource bundles");
        Map<String, List<String>> classesToResourceBundlesMap = fillClassesToResourceMapper();
        classesToResourceBundlesMap.keySet().stream().filter(exceptionClass -> classesToResourceBundlesMap.get(exceptionClass).size() < NUMBER_OF_RESOURCE_BUNDLES_SECTION).forEach(failedExceptionClass ->
                resourceBundleValidationException.addException(new ResourceBundleValidationException("class: " + failedExceptionClass + " is not linked properly in " + projectPersistPath))
        );
        if (!resourceBundleValidationException.getExceptions().isEmpty()) {
            throw resourceBundleValidationException;
        }
    }

    private Map<String, List<String>> fillClassesToResourceMapper() {
        Map<String, List<String>> classesToResourceBundlesMap = initClassesToResourceMapper();
        filesProvider.provide("^messages(_(en|ar))?\\.properties$").forEach(foundResourceBundle -> {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(foundResourceBundle))) {
                bufferedReader.lines().filter(line -> !line.trim().startsWith("#")).forEach(line -> {
                    String[] lineMessageParts = line.split("[-=]");
                    classesToResourceBundlesMap.get(lineMessageParts[0]).add(lineMessageParts[1]);
                });
            } catch (IOException ioException) {
                logger.error("Un error happened while reading resource bundles files");
            }
        });
        return classesToResourceBundlesMap;
    }

    private Map<String, List<String>> initClassesToResourceMapper() {
        return filesProvider.provide("^.*Exception.*\\.java$").stream().map(File::getName).collect(Collectors.toMap(className -> className, className -> new ArrayList<>()));
    }
}
