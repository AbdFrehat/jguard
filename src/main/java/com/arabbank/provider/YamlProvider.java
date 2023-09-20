package com.arabbank.provider;

import java.util.List;
import java.util.Map;

public class YamlProvider {

    @SuppressWarnings("unchecked")
    public String provide(String[] properties, Map<String, Object> propertiesMap) {
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
