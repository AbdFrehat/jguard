package com.arabbank.provider;

import com.arabbank.function.ParseYamlFunction;
import com.arabbank.model.ApplicationYaml;
import com.arabbank.model.enums.ConfigProps;

import java.util.Map;

public class ApplicationYamlProvider {
    private final ParseYamlFunction parseYamlFunction;
    private final ConfigurationProvider configurationProvider;
    private ApplicationYaml applicationYamlProperties;
    private final YamlProvider yamlProvider;

    public ApplicationYamlProvider() {
        this.parseYamlFunction = new ParseYamlFunction();
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
        applicationYamlProperties = parseYamlFunction.parse(
                configurationProvider.provide(ConfigProps.PERSIST_PATH) + "/src/main/resources/config.yml"
        );
    }

}
