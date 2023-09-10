package com.arabbank.executor;

import com.arabbank.function.YamlParseFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class YamlParseExecutor implements YamlParseFunction {
    private static final Logger logger = LoggerFactory.getLogger(YamlParseExecutor.class);

    @Override
    public Map<String, Object> parse(String yamlName) {
        Yaml yaml = new Yaml();
        try (InputStream input = new FileInputStream(yamlName)) {
            return yaml.load(input);
        } catch (FileNotFoundException e) {
            logger.info("Something went wrong, maybe {} doesn't exist", yamlName);
        } catch (IOException e) {
            logger.info("Error parsing the yaml file with path {}", yamlName);
        }
        return Map.of();
    }
}
