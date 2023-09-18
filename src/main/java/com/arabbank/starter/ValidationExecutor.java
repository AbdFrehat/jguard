package com.arabbank.starter;

import com.arabbank.validator.ApplicationYamlPropertiesValidator;
import com.arabbank.validator.ApplicationYamlValidator;
import com.arabbank.validator.RootFileValidator;

public class ValidationExecutor {
    private final RootFileValidator rootFileValidator;
    private final ApplicationYamlPropertiesValidator applicationYamlPropertiesValidator;
    private final ApplicationYamlValidator applicationYamlValidator;

    public ValidationExecutor() {
        this.applicationYamlPropertiesValidator = new ApplicationYamlPropertiesValidator();
        this.rootFileValidator = new RootFileValidator();
        this.applicationYamlValidator = new ApplicationYamlValidator();
    }

    public void execute() {
        rootFileValidator.validate();
        applicationYamlPropertiesValidator.validate();
//        pomFileValidator.validate();
    }
}
