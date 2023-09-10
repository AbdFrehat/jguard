package com.arabbank.provider;

import com.arabbank.function.YamlParseFunction;

import java.util.Map;

public class ConfigurationProvider {
    private final YamlParseFunction yamlParseFunction;
    private Map<String, Object> configurations;
    private YamlProvider yamlProvider;

    public ConfigurationProvider(YamlParseFunction yamlParseFunction) {
        this.yamlParseFunction = yamlParseFunction;
        this.yamlProvider = new YamlProvider();
        parseConfigurations();
    }

    public String provide(String propertyName) {
        String[] properties = propertyName.split("\\.");
        Map<String, Object> temp;
        temp = configurations;
        return yamlProvider.provide(properties, temp, propertyName);
    }

    private void parseConfigurations() {
        configurations = yamlParseFunction.parse("/Users/moh/jguard/src/main/resources/config.yml");
    }
}
