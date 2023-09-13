package com.arabbank.provider;

import com.arabbank.executor.YamlParseExecutor;
import com.arabbank.function.YamlParseFunction;
import com.arabbank.model.YamlFile;
import com.arabbank.model.enums.ConfigProps;

import java.util.Map;

public class ApplicationYamlProvider {
    private final YamlParseFunction yamlParseFunction;
    private final ConfigurationProvider configurationProvider;
    private YamlFile applicationYamlProperties;
    private final YamlProvider yamlProvider;

    public ApplicationYamlProvider() {
        this.yamlParseFunction = new YamlParseExecutor();
        this.configurationProvider = new ConfigurationProvider();
        this.yamlProvider = new YamlProvider();
        parseApplicationYaml();
    }

    public String provide(ConfigProps propertyName) {
        String[] properties = propertyName.name().split("\\.");
        Map<String, Object> temp = applicationYamlProperties.properties();
        return yamlProvider.provide(properties, temp, propertyName);
    }

    private void parseApplicationYaml() {
        applicationYamlProperties = yamlParseFunction.parse(
                configurationProvider.provide(ConfigProps.PERSIST_PATH) + "/src/main/resources/config.yml"
        );
    }

}
