package com.arabbank.provider;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public final class YamlParser {
    public static Map<String, Object> configurations;

    public static Map<String, Object> parseYaml(String yamlPath) {
        Yaml yaml = new Yaml();
        InputStream inputStream = YamlParser.class
                .getClassLoader()
                .getResourceAsStream(yamlPath);
        return yaml.load(inputStream);
    }

    private YamlParser() {
    }
}
