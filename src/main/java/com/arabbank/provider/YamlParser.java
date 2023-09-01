package com.arabbank.provider;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public final class YamlParser {
    public static Map<String, Object> configurations;

    private static void parseYaml() {
        Yaml yaml = new Yaml();
        InputStream inputStream = YamlParser.class
                .getClassLoader()
                .getResourceAsStream("config.yml");
        configurations = yaml.load(inputStream);
        System.out.println(configurations);
    }

    public YamlParser() {
        parseYaml();
    }
}
