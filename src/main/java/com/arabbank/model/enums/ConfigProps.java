package com.arabbank.model.enums;

import java.util.Arrays;

public enum ConfigProps {
    REPOSITORY_URL("repositoryUrls"),
    PERSIST_PATH("persistPath"),
    PROPERTIES_TO_VALIDATE("propertiesToValidate"),
    FILES_TO_VALIDATE("filesToValidate");

    private final String value;

    ConfigProps(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ConfigProps fromValue(String value) {
        return Arrays.stream(values())
                .filter(prop -> prop.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ConfigProps value: " + value));
    }
}
