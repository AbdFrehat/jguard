package com.arabbank.validator;

import com.arabbank.executor.YamlParseExecutor;
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
        this.configurationProvider = new ConfigurationProvider(new YamlParseExecutor());
    }

    public void validate() {
        List<String> filesToValidate = List.of(configurationProvider.provide("filesToValidate").split(","));
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
