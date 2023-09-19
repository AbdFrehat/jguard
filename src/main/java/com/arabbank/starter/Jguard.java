package com.arabbank.starter;

import com.arabbank.executor.CloneProjectExecutor;
import com.arabbank.executor.ParseApplicationYamlExecutor;
import com.arabbank.executor.ParseConfigYamlExecutor;
import com.arabbank.executor.ReadTreeExecutor;
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
                new ParseApplicationYamlExecutor(new FilesProvider()));
        ParseConfigYamlExecutor.configYaml.getProjects().forEach(
                project -> projectProcessor.run(project, configYaml.getPersistPath()));
    }

    static {
        String logo = LogoCreator.createLogo();
        logger.info(logo);
    }
}
