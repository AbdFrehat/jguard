package com.arabbank.function;

import com.arabbank.model.ApplicationYaml;

@FunctionalInterface
public interface YamlParseFunction {
    ApplicationYaml parse(String yamlName);
}
