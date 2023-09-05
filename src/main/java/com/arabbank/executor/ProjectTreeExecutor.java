package com.arabbank.executor;

import com.arabbank.function.FileExistenceValidator;
import com.arabbank.function.ProjectTreeAnalyzer;
import com.arabbank.model.ProjectTree;

import java.io.File;
import java.util.List;

public class ProjectTreeExecutor implements ProjectTreeAnalyzer {
    private final ProjectTreeFilter projectTreeFilter;
    private final List<FileExistenceValidator> fileExistenceValidators;

    public ProjectTreeExecutor(ProjectTreeFilter projectTreeFilter, List<FileExistenceValidator> fileExistenceValidators) {
        this.projectTreeFilter = projectTreeFilter;
        this.fileExistenceValidators = fileExistenceValidators;
    }

    @Override
    public ProjectTree analyzeProjectTree(String path) {
        analyzeDirectory(new File(path));
        return null;
    }

    public void analyzeDirectory(File directory) {
        File[] files = directory.listFiles(projectTreeFilter);
        if (files != null) {
            for (File file : files) {
                if (isValid(directory, file)) {
                    System.out.println("File: " + file.getName());
                } else if (file.isDirectory()) {
                    analyzeDirectory(file);
                }
            }
        }
    }

    private boolean isValid(File directory, File file) {
        return file.isFile() && file.isFile() && !directory.isHidden() && fileExistenceValidators.stream().allMatch(path -> path.validate(file));
    }
}
