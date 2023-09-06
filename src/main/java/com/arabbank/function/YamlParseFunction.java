package com.arabbank.function;

import java.util.Map;

@FunctionalInterface
public interface YamlParseFunction {
    Map<String, Object> parse(String yamlName);
}
