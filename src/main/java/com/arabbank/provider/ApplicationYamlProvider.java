package com.arabbank.provider;

import com.arabbank.function.YamlParseFunction;

import java.util.Map;

public class ApplicationYamlProvider {
    private final YamlParseFunction yamlParseFunction;
    private final ConfigurationProvider configurationProvider;
    private Map<String, Object> configurations;
    private final YamlProvider yamlProvider;

    public ApplicationYamlProvider(YamlParseFunction yamlParseFunction) {
        this.yamlParseFunction = yamlParseFunction;
        this.configurationProvider = new ConfigurationProvider(yamlParseFunction);
        this.yamlProvider = new YamlProvider();
        parseApplicationYaml();
    }

    public String provide(String propertyName) {
        String[] properties = propertyName.split("\\.");
        Map<String, Object> temp;
        temp = configurations;
        return yamlProvider.provide(properties, temp, propertyName);
    }

    private void parseApplicationYaml() {
        configurations = yamlParseFunction.parse(
                configurationProvider.provide("persistPath") + "/src/main/resources/config.yml");
    }

}
