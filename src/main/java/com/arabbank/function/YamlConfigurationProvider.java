package com.arabbank.function;

@FunctionalInterface
public interface YamlConfigurationProvider {
    String provide(String configurationName);
}
