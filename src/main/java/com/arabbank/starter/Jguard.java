package com.arabbank.starter;

import com.arabbank.executor.*;
import com.arabbank.model.ConfigYaml;
import com.arabbank.process.ParseConfigYamlProcess;
import com.arabbank.provider.FilesProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jguard {
    private static final Logger logger = LoggerFactory.getLogger(Jguard.class);

    private Jguard() {
    }

    static {
        ParseConfigYamlProcess parseConfigYamlProcess = new ParseConfigYamlExecutor();
        parseConfigYamlProcess.process();
    }

    public static void start() {
        ConfigYaml configYaml = ParseConfigYamlExecutor.configYaml;
        ProjectProcessor projectProcessor = new ProjectProcessor(
                new CloneProjectExecutor(),
                new ReadTreeExecutor(),
                new FilesProvider(),
                new ParseApplicationYamlExecutor(new FilesProvider()),
                new ParsePomExecutor());
        ParseConfigYamlExecutor.configYaml.getProjects().forEach(
                project -> projectProcessor.run(project, configYaml.getPersistPath()));
    }

    static {
        String logo = LogoCreator.createLogo();
        logger.info(logo);
    }
}
