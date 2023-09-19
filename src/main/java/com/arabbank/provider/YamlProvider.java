package com.arabbank.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class YamlProvider {
    private static final Logger logger = LoggerFactory.getLogger(YamlProvider.class);

    @SuppressWarnings("unchecked")
    public String provide(String[] properties, Map<String, Object> propertiesMap, String propertyName) {
        String propertyValue = "";
        for (String property : properties) {
            Object value = propertiesMap.getOrDefault(property, "");
            if (value instanceof Map) {
                propertiesMap = (Map<String, Object>) value;
            } else if (value instanceof List) {
                propertyValue = String.join(",", ((List<String>) value));
            } else {
                propertyValue = value.toString();
            }
        }
        return propertyValue;
    }
}
