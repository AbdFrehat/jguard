package com.arabbank.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arabbank.executor.CloneProjectExecutor;
import com.arabbank.executor.ParseYamlExecutor;
import com.arabbank.executor.ReadTreeExecutor;
import com.arabbank.function.ParseYamlFunction;
import com.arabbank.model.ConfigYaml;
import com.arabbank.process.ParseYamlProcess;
import com.arabbank.provider.FilesProvider;

public class Jguard {
    private static final Logger logger = LoggerFactory.getLogger(Jguard.class);

    private Jguard() {
    }

    static {
        ParseYamlProcess parseYamlProcess = new ParseYamlExecutor();
        parseYamlProcess.process();
    }

    public static void start() {
        ConfigYaml configYaml = ParseYamlExecutor.configYaml;
        ProjectProcessor projectProcessor = new ProjectProcessor(new CloneProjectExecutor(), new ReadTreeExecutor(),
                new ParseYamlFunction(), new FilesProvider());
        ParseYamlExecutor.configYaml.getProjects().forEach(
                project -> projectProcessor.run(project, configYaml.getPersistPath()));
    }

    static {
        String logo = LogoCreator.createLogo();
        logger.info(logo);
    }
}
