package com.arabbank.validator;

import com.arabbank.executor.ParsePomExecutor;
import com.arabbank.provider.FilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class VersionValidator {
    private final FilesProvider filesProvider;
    private static final Logger logger = LoggerFactory.getLogger(VersionValidator.class);

    public VersionValidator() {
        this.filesProvider = new FilesProvider();
    }

    public void validate() {
        try {
            FileReader fileReader = new FileReader(filesProvider.provide("VERSION").get(0));
            String version = new BufferedReader(fileReader).readLine();
            ParsePomExecutor.pomFile.tags().get("version");
        } catch (FileNotFoundException e) {
            logger.error("File wasn't found");
        } catch (IOException e) {
            logger.error("Error reading version file");
        }
    }
}
