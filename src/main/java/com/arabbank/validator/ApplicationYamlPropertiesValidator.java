package com.arabbank.validator;

import com.arabbank.model.enums.ConfigProps;
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
        this.configurationProvider = new ConfigurationProvider();
        this.applicationYamlProvider = new ApplicationYamlProvider();
    }

    public void validate() {
        List<String> propertiesToValidate = List.of(configurationProvider.provide(ConfigProps.PROPERTIES_TO_VALIDATE).split(","));
        propertiesToValidate.forEach(property -> {
            String propertyValue = applicationYamlProvider.provide(ConfigProps.fromValue(property.trim()));
            if (!propertyValue.isEmpty()) {
                logger.info("Property {} exists with the value {}", property, propertyValue);
            }
        });
    }
}
