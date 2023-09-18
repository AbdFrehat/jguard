package com.arabbank.function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.arabbank.model.ApplicationYaml;

public class ParseYamlFunction {
    private static final Logger logger = LoggerFactory.getLogger(ParseYamlFunction.class);

    public ApplicationYaml parse(String yamlPath) {
        Yaml yaml = new Yaml();
        try (InputStream input = new FileInputStream(yamlPath)) {
            Map<String, Object> properties = yaml.load(input);
            return new ApplicationYaml(properties);
        } catch (FileNotFoundException e) {
            logger.error("Something went wrong, maybe {} doesn't exist", yamlPath);
        } catch (IOException e) {
            logger.error("Error parsing the yaml file with path {}", yamlPath);
        }
        return new ApplicationYaml(Map.of());
    }
}
