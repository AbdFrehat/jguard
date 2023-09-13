package com.arabbank.provider;

import com.arabbank.executor.ProjectTreeExecutor;
import com.arabbank.model.ConfigProps;
import com.arabbank.model.ProjectTree;

import java.io.File;
import java.util.List;

public class FilesProvider {

    private final ProjectTreeExecutor projectTreeExecutor;
    private final ConfigurationProvider configurationProvider;
    private ProjectTree projectTree;

    public FilesProvider() {
        this.configurationProvider = new ConfigurationProvider();
        this.projectTreeExecutor = new ProjectTreeExecutor();
        readProject();
    }

    public List<File> provide(String filename) {
        return projectTree.projectDirectories().get(filename);
    }

    private void readProject() {
        projectTree = projectTreeExecutor.scan(configurationProvider.provide(ConfigProps.PERSIST_PATH));
    }
}
