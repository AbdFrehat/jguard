package com.arabbank.validator;

import com.arabbank.provider.ConfigurationProvider;
import com.arabbank.provider.PomFileProvider;

public class PomFileValidator {
    private final PomFileProvider pomFileProvider;
    private final ConfigurationProvider configurationProvider;

    public PomFileValidator() {
        this.configurationProvider = new ConfigurationProvider();
        this.pomFileProvider = new PomFileProvider();
    }

    public void validate() {

    }

    ;
}
