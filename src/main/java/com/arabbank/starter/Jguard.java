package com.arabbank.starter;

import com.arabbank.function.GitCloneFunction;
import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.provider.YamlParser;

import java.util.Map;

public class Jguard {
    private static Map<String, Object> configs;
    private static Map<String, Object> yamlProperties;

    private final GitCloneFunction gitCloneFunction;
    private final ProjectTreeFunction projectTreeFunction;

    public Jguard(GitCloneFunction gitCloneFunction, ProjectTreeFunction projectTreeFunction) {
        this.gitCloneFunction = gitCloneFunction;
        this.projectTreeFunction = projectTreeFunction;
    }

    public void start(String configPath) {
        displayLogo();
        configs = YamlParser.parseYaml("config.yml");
        gitCloneFunction.cloneGitRepository((String) configs.get("repositoryUrl"), (String) configs.get("persistPath"));
        projectTreeFunction.analyze((String) configs.get("persistPath"));
    }

    private static void displayLogo() {
        System.out.println(LogoCreator.createLogo());
    }
}
