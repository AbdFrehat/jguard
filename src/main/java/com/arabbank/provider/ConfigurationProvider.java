package com.arabbank.provider;

import com.arabbank.model.enums.ConfigProps;

import java.util.Map;

public class ConfigurationProvider {
    private Map<String, Object> configurations;
    private final YamlProvider yamlProvider;

    public ConfigurationProvider() {
        this.yamlProvider = new YamlProvider();
    }

    public String provide(ConfigProps propertyName) {
        String[] properties = propertyName.value().split("\\.");
        Map<String, Object> temp = configurations;
        return yamlProvider.provide(properties, temp, propertyName);
    }

}
