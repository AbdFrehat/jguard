package com.arabbank.executor;

import com.arabbank.function.GitCloneFunction;
import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.model.ConfigProps;
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
                configurationProvider.provide(ConfigProps.REPOSITORY_URL),
                configurationProvider.provide(ConfigProps.PERSIST_PATH));
        projectTreeFunction.scan(configurationProvider.provide(ConfigProps.PERSIST_PATH));
    }
}
