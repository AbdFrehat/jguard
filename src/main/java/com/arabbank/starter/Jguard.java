package com.arabbank.starter;

import com.arabbank.executor.ParseYamlExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jguard {
    private static final Logger logger = LoggerFactory.getLogger(Jguard.class);


    private Jguard() {
    }

    public static void start() {
        ProjectProcessor projectProcessor = new ProjectProcessor();
        ParseYamlExecutor.configYaml.getProjects().forEach(
                projectProcessor::run
        );
    }

    static {
        String logo = LogoCreator.createLogo();
        logger.info(logo);
    }
}
