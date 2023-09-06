package com.arabbank.executor;

import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.model.ProjectTree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectTreeExecutor implements ProjectTreeFunction {

    @Override
    public ProjectTree analyze(String path) {
        ProjectTree projectTree = new ProjectTree("", new HashMap<>());
        analyzeDirectory(new File(path), projectTree);
        System.out.println(projectTree);
        return null;
    }

    private void analyzeDirectory(File directory, ProjectTree projectTree) {
        File[] files = directory.listFiles();
        List<String> filesOfDirectory = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filesOfDirectory.add(file.getName());
                    System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    analyzeDirectory(file, projectTree);
                }
            }
            projectTree.projectDirectories().put(directory.getName(), filesOfDirectory);
        }
    }
}
