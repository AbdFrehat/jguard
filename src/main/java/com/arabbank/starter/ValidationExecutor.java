package com.arabbank.starter;

import com.arabbank.validator.ApplicationYamlPropertiesValidator;
import com.arabbank.validator.RootFileValidator;

public class ValidationExecutor {
    private final RootFileValidator rootFileValidator;
    private final ApplicationYamlPropertiesValidator applicationYamlPropertiesValidator;

    public ValidationExecutor() {
        this.applicationYamlPropertiesValidator = new ApplicationYamlPropertiesValidator();
        this.rootFileValidator = new RootFileValidator();
    }

    public void execute() {
        rootFileValidator.validate();
        applicationYamlPropertiesValidator.validate();
    }
}
