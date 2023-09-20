package com.arabbank.validator;

import com.arabbank.exception.ApplicationYamlValidationException;
import com.arabbank.model.ApplicationYaml;
import com.arabbank.provider.ApplicationYamlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApplicationYamlPropertiesValidator {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationYamlPropertiesValidator.class);
    private final ApplicationYamlProvider applicationYamlProvider;

    public ApplicationYamlPropertiesValidator(ApplicationYaml applicationYaml) {
        this.applicationYamlProvider = new ApplicationYamlProvider(applicationYaml);
    }

    public void validate(List<String> propertiesToValidate) throws ApplicationYamlValidationException {
        propertiesToValidate.forEach(property -> {
            String propertyValue = applicationYamlProvider.provide(property);
            if (!propertyValue.isEmpty()) {
                logger.info("Property {} exists with the value {}", property, propertyValue);
                return;
            }
            logger.warn("Property {} doesn't exist ", property);
        });
    }
}
