package com.arabbank.validator;

import com.arabbank.model.enums.ConfigProps;
import com.arabbank.provider.ConfigurationProvider;
import com.arabbank.provider.FilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class RootFileValidator {
    private final FilesProvider filesProvider;
    private final ConfigurationProvider configurationProvider;
    private static final Logger logger = LoggerFactory.getLogger(RootFileValidator.class);

    public RootFileValidator() {
        this.filesProvider = new FilesProvider();
        this.configurationProvider = new ConfigurationProvider();
    }

    public void validate() {
        List<String> filesToValidate = List.of(configurationProvider.provide(ConfigProps.FILES_TO_VALIDATE).split(","));
        List<File> root = this.filesProvider.provide("test1");
        List<String> names = root.stream().map(File::getName).toList();
        logger.info("Validating files: {}", filesToValidate);
        filesToValidate.forEach(file -> {
            logger.info("Validating {}", file);
            if (names.contains(file.trim())) {
                logger.info("{} exists", file);
                return;
            }
            logger.error("{} not found", file);
        });
    }
}
