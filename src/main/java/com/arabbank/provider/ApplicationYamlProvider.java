package com.arabbank.provider;

import com.arabbank.function.YamlParseFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ApplicationYamlProvider {
    private final YamlParseFunction yamlParseFunction;
    private final ConfigurationProvider configurationProvider;
    private Map<String, Object> configurations;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationYamlProvider.class);

    public ApplicationYamlProvider(YamlParseFunction yamlParseFunction) {
        this.yamlParseFunction = yamlParseFunction;
        this.configurationProvider = new ConfigurationProvider(yamlParseFunction);
        parseApplicationYaml();
    }

    public String provide(String propertyName) {
        String[] properties = propertyName.split("\\.");
        Map<String, Object> temp;
        temp = configurations;
        String propertyValue = "";
        for (String property : properties) {
            Object value = temp.get(property);
            if (value instanceof Map) {
                temp = (Map<String, Object>) value;
            } else {
                propertyValue = value.toString();
            }
        }
        logger.info("property {} value is {}", propertyName, propertyValue);
        return propertyValue;
    }

    private void parseApplicationYaml() {
        configurations = yamlParseFunction.parse(configurationProvider.provide("persistPath") + "/src/main/resources/config.yml");
    }

}
