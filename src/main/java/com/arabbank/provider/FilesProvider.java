package com.arabbank.provider;

import com.arabbank.executor.ReadTreeExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FilesProvider {
    private final List<File> foundFilesList = new ArrayList<>();

    public List<File> provide(String fileName) {
        return searchInFileSystemTree(fileName, ReadTreeExecutor.projectTree.projectDirectories());
    }

    private List<File> searchInFileSystemTree(String fileName, List<File> projectFilesList) {
        for (File file : projectFilesList) {
            if (file.isDirectory()) {
                searchInFileSystemTree(fileName, Arrays.asList(Objects.requireNonNull(file.listFiles())));
            } else {
                if (file.getName().equals(fileName)) {
                    foundFilesList.add(file);
                }
            }
        }
        return foundFilesList;
    }
}
