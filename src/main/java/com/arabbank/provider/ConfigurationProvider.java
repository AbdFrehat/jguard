package com.arabbank.provider;

import com.arabbank.function.YamlParseFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ConfigurationProvider {
    private final YamlParseFunction yamlParseFunction;
    private Map<String, Object> configurations;
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationProvider.class);

    public ConfigurationProvider(YamlParseFunction yamlParseFunction) {
        this.yamlParseFunction = yamlParseFunction;
        parseConfigurations();
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

    private void parseConfigurations() {
        configurations = yamlParseFunction.parse("/Users/moh/jguard/src/main/resources/config.yml");
    }
}
