package com.arabbank.executor;

import com.arabbank.function.ParseYamlFunction;
import com.arabbank.model.ConfigYaml;
import com.arabbank.process.ParseYamlProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.MissingEnvironmentVariableException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static java.io.File.separator;

public class ParseYamlExecutor implements ParseYamlProcess {
    private static final Logger logger = LoggerFactory.getLogger(ParseYamlFunction.class);
    public static ConfigYaml configYaml;

    @Override
    public void process() {
        final String configHomePath = System.getenv().get("CONFIG_HOME");
        final String yamlFilePath = configHomePath + separator + "config.yml";
        if (configHomePath == null || configHomePath.isEmpty()) {
            throw new MissingEnvironmentVariableException("Provide CONFIG_HOME in order to locate config.yml file");
        }
        try (InputStream input = new FileInputStream(yamlFilePath)) {
            Yaml yaml = new Yaml();
            configYaml = yaml.loadAs(input, ConfigYaml.class);
        } catch (FileNotFoundException e) {
            logger.error("Something went wrong, maybe {} doesn't exist", yamlFilePath);
        } catch (IOException e) {
            logger.error("Error parsing the yaml file with path {}", yamlFilePath);
        }
    }
}
