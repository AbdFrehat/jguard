package com.arabbank.executor;

import com.arabbank.function.GitCloneFunction;
import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.provider.ConfigurationProvider;

public class FunctionExecutor {
    private final GitCloneFunction gitCloneFunction;
    private final ProjectTreeFunction projectTreeFunction;
    private final ConfigurationProvider configurationProvider;

    public FunctionExecutor() {
        this.configurationProvider = new ConfigurationProvider();
        this.gitCloneFunction = new GitCloneExecutor();
        this.projectTreeFunction = new ProjectTreeExecutor();
    }

    public void execute() {
        gitCloneFunction.cloneGitRepository(
                configurationProvider.provide("repositoryUrl"),
                configurationProvider.provide("persistPath"));
        projectTreeFunction.scan(configurationProvider.provide("persistPath"));
    }
}
