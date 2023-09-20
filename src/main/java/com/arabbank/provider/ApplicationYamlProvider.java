package com.arabbank.provider;

import com.arabbank.model.ApplicationYaml;

import java.util.Map;

public class ApplicationYamlProvider {
    private final ApplicationYaml applicationYaml;
    private final YamlProvider yamlProvider;

    public ApplicationYamlProvider(ApplicationYaml applicationYaml) {
        this.yamlProvider = new YamlProvider();
        this.applicationYaml = applicationYaml;
    }

    public String provide(String propertyName) {
        String[] properties = propertyName.split("\\.");
        Map<String, Object> temp = applicationYaml.properties();
        return yamlProvider.provide(properties, temp);
    }

}
