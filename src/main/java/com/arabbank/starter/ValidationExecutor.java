package com.arabbank.starter;

import com.arabbank.validator.ApplicationYamlPropertiesValidator;
import com.arabbank.validator.PomFileValidator;
import com.arabbank.validator.RootFileValidator;

public class ValidationExecutor {
    private final RootFileValidator rootFileValidator;
    private final ApplicationYamlPropertiesValidator applicationYamlPropertiesValidator;
    private final PomFileValidator pomFileValidator;

    public ValidationExecutor() {
        this.applicationYamlPropertiesValidator = new ApplicationYamlPropertiesValidator();
        this.rootFileValidator = new RootFileValidator();
        this.pomFileValidator = new PomFileValidator();
    }

    public void execute() {
        rootFileValidator.validate();
        applicationYamlPropertiesValidator.validate();
//        pomFileValidator.validate();
    }
}
