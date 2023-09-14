package com.arabbank.provider;

import com.arabbank.executor.YamlParseExecutor;
import com.arabbank.function.YamlParseFunction;
import com.arabbank.model.YamlFile;
import com.arabbank.model.enums.ConfigProps;
import org.yaml.snakeyaml.error.MissingEnvironmentVariableException;

import static java.io.File.separator;
import java.util.Map;

public class ConfigurationProvider {
    private final YamlParseFunction yamlParseFunction;
    private YamlFile configurations;
    private final YamlProvider yamlProvider;

    public ConfigurationProvider() {
        this.yamlParseFunction = new YamlParseExecutor();
        this.yamlProvider = new YamlProvider();
        parseConfigurations();
    }

    public String provide(ConfigProps propertyName) {
        String[] properties = propertyName.value().split("\\.");
        Map<String, Object> temp = configurations.properties();
        return yamlProvider.provide(properties, temp, propertyName);
    }

    private void parseConfigurations() {
        final String configHomePath = System.getenv().get("CONFIG_HOME");
        if(configHomePath == null || configHomePath.isEmpty()) {
            throw new MissingEnvironmentVariableException("Provide CONFIG_HOME in order to locate config.yml file");
        }
        configurations = yamlParseFunction.parse(configHomePath + separator + "config.yml");
    }
}
