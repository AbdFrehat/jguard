package com.arabbank.executor;

import com.arabbank.model.ApplicationYaml;
import com.arabbank.process.ParseApplicationYamlProcess;
import com.arabbank.provider.FilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ParseApplicationYamlExecutor implements ParseApplicationYamlProcess {
    private static final Logger logger = LoggerFactory.getLogger(ParseApplicationYamlExecutor.class);
    public static ApplicationYaml applicationYaml;
    private final FilesProvider filesProvider;

    public ParseApplicationYamlExecutor(FilesProvider filesProvider) {
        this.filesProvider = filesProvider;
    }

    @Override
    public void process() {
        List<File> searchResult = filesProvider
                .provide("\\application\\.(yaml|yml)$");
        String yamlFilePath = searchResult.isEmpty() ? "" : searchResult.get(0).getAbsolutePath();
        try (InputStream input = new FileInputStream(yamlFilePath)) {
            Yaml yaml = new Yaml();
            applicationYaml = new ApplicationYaml(yaml.load(input));
        } catch (FileNotFoundException e) {
            applicationYaml = new ApplicationYaml(Map.of());
            logger.error("Something went wrong, maybe application yaml doesn't exist");
        } catch (IOException e) {
            applicationYaml = new ApplicationYaml(Map.of());
            logger.error("Error parsing the yaml file with path {}", yamlFilePath);
        }
    }
}
