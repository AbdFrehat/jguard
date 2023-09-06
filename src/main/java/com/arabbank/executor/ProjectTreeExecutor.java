package com.arabbank.executor;

import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.model.ProjectTree;

import java.io.File;

public class ProjectTreeExecutor implements ProjectTreeFunction {

    @Override
    public ProjectTree analyze(String path) {
        analyzeDirectory(new File(path));
        return null;
    }

    private void analyzeDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.isFile() && !directory.isHidden()) {
                } else if (file.isDirectory()) {
                    analyzeDirectory(file);
                }
            }
        }
    }
}
