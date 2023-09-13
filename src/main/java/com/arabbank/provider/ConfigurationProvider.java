package com.arabbank.provider;

import com.arabbank.executor.YamlParseExecutor;
import com.arabbank.function.YamlParseFunction;
import com.arabbank.model.ConfigProps;
import com.arabbank.model.YamlFile;

import java.util.Map;

public class ConfigurationProvider {
    private final YamlParseFunction yamlParseFunction;
    private YamlFile configurations;
    private final YamlProvider yamlProvider;

    public ConfigurationProvider() {
        this.yamlParseFunction = new YamlParseExecutor();
        this.yamlProvider = new YamlProvider();
        parseConfigurations();
    }

    public String provide(ConfigProps propertyName) {
        String[] properties = propertyName.name().split("\\.");
        Map<String, Object> temp = configurations.properties();
        return yamlProvider.provide(properties, temp, propertyName);
    }

    private void parseConfigurations() {
        configurations = yamlParseFunction.parse("/Users/moh/jguard/src/main/resources/config.yml");
    }
}
