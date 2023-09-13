package com.arabbank.function;

import com.arabbank.model.YamlFile;

@FunctionalInterface
public interface YamlParseFunction {
    YamlFile parse(String yamlName);
}
