package com.arabbank.provider;

import com.arabbank.model.ConfigProps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class YamlProvider {
    private static final Logger logger = LoggerFactory.getLogger(YamlProvider.class);

    public String provide(String[] properties, Map<String, Object> propertiesMap, ConfigProps propertyName) {
        String propertyValue = "";
        for (String property : properties) {
            Object value = propertiesMap.getOrDefault(property, "");
            if (value instanceof Map) {
                propertiesMap = (Map<String, Object>) value;
            } else {
                propertyValue = value.toString();
            }
        }
        logger.info("property {} value is {}", propertyName, propertyValue);
        return propertyValue;
    }
}
