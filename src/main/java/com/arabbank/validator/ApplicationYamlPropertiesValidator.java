package com.arabbank.validator;

import com.arabbank.executor.YamlParseExecutor;
import com.arabbank.provider.ApplicationYamlProvider;
import com.arabbank.provider.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApplicationYamlPropertiesValidator {
    private final ConfigurationProvider configurationProvider;
    private final ApplicationYamlProvider applicationYamlProvider;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationYamlPropertiesValidator.class);

    public ApplicationYamlPropertiesValidator() {
        this.configurationProvider = new ConfigurationProvider(new YamlParseExecutor());
        this.applicationYamlProvider = new ApplicationYamlProvider(new YamlParseExecutor());
    }

    public void validate() {
        List<String> propertiesToValidate = List.of(configurationProvider.provide("propertiesToValidate").split(","));
        propertiesToValidate.forEach(property -> {
            String propertyValue = applicationYamlProvider.provide(property.trim());
            if (!propertyValue.isEmpty()) {
                logger.info("Property {} exists with the value {}", property, propertyValue);
            }
        });
    }
}
