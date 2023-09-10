package com.arabbank.provider;

import com.arabbank.executor.ProjectTreeExecutor;
import com.arabbank.executor.YamlParseExecutor;
import com.arabbank.model.ProjectTree;

import java.util.List;

public class FilesProvider {

    private final ProjectTreeExecutor projectTreeExecutor;
    private final ConfigurationProvider configurationProvider;
    private ProjectTree projectTree;

    public FilesProvider() {
        this.configurationProvider = new ConfigurationProvider(new YamlParseExecutor());
        this.projectTreeExecutor = new ProjectTreeExecutor();
        readProject();
    }

    public List<String> provide(String filename) {
        return projectTree.projectDirectories().get(filename);
    }

    private void readProject() {
        projectTree = projectTreeExecutor.analyze(configurationProvider.provide("persistPath"));
    }
}
