package com.arabbank.provider;

import com.arabbank.executor.ProjectTreeExecutor;
import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.model.ProjectTree;
import com.arabbank.model.enums.ConfigProps;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FilesProvider {

    private final ProjectTreeFunction projectTreeFunction;
    private final ConfigurationProvider configurationProvider;
    private ProjectTree projectTree;

    private final List<File> foundFilesList = new ArrayList<>();

    public FilesProvider() {
        this.configurationProvider = new ConfigurationProvider();
        this.projectTreeFunction = new ProjectTreeExecutor();
        readProject();
    }

    public List<File> provide(String fileName) {
        return searchInFileSystemTree(fileName, projectTree.projectDirectories());
    }

    private void readProject() {
        projectTree = projectTreeFunction.scan(configurationProvider.provide(ConfigProps.PERSIST_PATH));
    }

    private List<File> searchInFileSystemTree(String fileName, List<File> projectFilesList) {
        for (File file: projectFilesList) {
            if (file.isDirectory()) {
                searchInFileSystemTree(fileName, Arrays.asList(Objects.requireNonNull(file.listFiles())));
            } else {
                if(file.getName().equals(fileName)) {
                    foundFilesList.add(file);
                }
            }
        }
        return foundFilesList;
    }
}
