package com.arabbank.executor;

import com.arabbank.function.GitCloneFunction;
import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.model.enums.ConfigProps;
import com.arabbank.provider.ConfigurationProvider;
import static java.io.File.separator;
import java.util.Arrays;

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
        Arrays.stream(configurationProvider.provide(ConfigProps.REPOSITORY_URL).split(",")).forEach(repositoryURL -> {
            gitCloneFunction.cloneGitRepository(
                    repositoryURL,
                    configurationProvider.provide(ConfigProps.PERSIST_PATH) + separator + repositoryURL.replaceAll("https?:", ""));
            projectTreeFunction.scan(configurationProvider.provide(ConfigProps.PERSIST_PATH));
        });
    }
}
