package com.arabbank.starter;

import com.arabbank.executor.GitCloneExecutor;
import com.arabbank.executor.ProjectTreeExecutor;
import com.arabbank.executor.YamlParseExecutor;
import com.arabbank.function.GitCloneFunction;
import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.function.YamlParseFunction;

import java.util.Map;

public class Jguard {
    private static Map<String, Object> configs;
    private static Map<String, Object> yamlProperties;

    private static final GitCloneFunction gitCloneFunction = new GitCloneExecutor();
    private static final ProjectTreeFunction projectTreeFunction = new ProjectTreeExecutor();
    private static final YamlParseFunction yamlParseFunction = new YamlParseExecutor();


    public static void start() {
        displayLogo();
        configs = yamlParseFunction.parse("config.yml");
        gitCloneFunction.cloneGitRepository((String) configs.get("repositoryUrl"), (String) configs.get("persistPath"));
        projectTreeFunction.analyze((String) configs.get("persistPath"));
    }

    private static void displayLogo() {
        System.out.println(LogoCreator.createLogo());
    }
}
