package com.arabbank.provider;

import com.arabbank.function.YamlParseFunction;
import com.arabbank.model.YamlFile;

import java.util.Map;

public class ConfigurationProvider {
    private final YamlParseFunction yamlParseFunction;
    private YamlFile configurations;
    private final YamlProvider yamlProvider;

    public ConfigurationProvider(YamlParseFunction yamlParseFunction) {
        this.yamlParseFunction = yamlParseFunction;
        this.yamlProvider = new YamlProvider();
        parseConfigurations();
    }

    public String provide(String propertyName) {
        String[] properties = propertyName.split("\\.");
        Map<String, Object> temp = configurations.properties();
        return yamlProvider.provide(properties, temp, propertyName);
    }

    private void parseConfigurations() {
        configurations = yamlParseFunction.parse("/Users/moh/jguard/src/main/resources/config.yml");
    }
}
