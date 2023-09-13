package com.arabbank.provider;

import com.arabbank.function.YamlParseFunction;
import com.arabbank.model.YamlFile;

import java.util.Map;

public class ApplicationYamlProvider {
    private final YamlParseFunction yamlParseFunction;
    private final ConfigurationProvider configurationProvider;
    private YamlFile configurations;
    private final YamlProvider yamlProvider;

    public ApplicationYamlProvider(YamlParseFunction yamlParseFunction) {
        this.yamlParseFunction = yamlParseFunction;
        this.configurationProvider = new ConfigurationProvider(yamlParseFunction);
        this.yamlProvider = new YamlProvider();
        parseApplicationYaml();
    }

    public String provide(String propertyName) {
        String[] properties = propertyName.split("\\.");
        Map<String, Object> temp = configurations.properties();
        return yamlProvider.provide(properties, temp, propertyName);
    }

    private void parseApplicationYaml() {
        configurations = yamlParseFunction.parse(
                configurationProvider.provide("persistPath") + "/src/main/resources/config.yml");
    }

}
