package com.arabbank.provider;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.arabbank.executor.ReadTreeExecutor;

public class FilesProvider {
    private final List<File> foundFilesList = new ArrayList<>();

    public List<File> provide(String fileName) {
        foundFilesList.clear();
        return searchInFileSystemTree(fileName, ReadTreeExecutor.projectTree.projectDirectories());
    }

    private List<File> searchInFileSystemTree(String fileName, List<File> projectFilesList) {
        for (File file : projectFilesList) {
            if (file.isDirectory()) {
                searchInFileSystemTree(fileName, Arrays.asList(Objects.requireNonNull(file.listFiles())));
            } else {
                if (file.getName().matches(fileName)) {
                    foundFilesList.add(file);
                }
            }
        }
        return foundFilesList;
    }
}
