package com.arabbank.executor;

import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.model.ProjectTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectTreeExecutor implements ProjectTreeFunction {
    private static final Logger logger = LoggerFactory.getLogger(ProjectTreeExecutor.class);

    @Override
    public ProjectTree scan(String path) {
        logger.info("Analyzing repository file tree from [{}]", path);
        ProjectTree projectTree = new ProjectTree(new HashMap<>());
        scanDirectory(new File(path), projectTree);
        logger.info("Finished analyzing project tree");
        return projectTree;
    }

    private void scanDirectory(File directory, ProjectTree projectTree) {
        File[] files = directory.listFiles();
        List<File> filesOfDirectory = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filesOfDirectory.add(file);
                } else if (file.isDirectory()) {
                    filesOfDirectory.add(file);
                    logger.info("Parsing Directory {}", file.getName());
                    scanDirectory(file, projectTree);
                }
            }
            projectTree.projectDirectories().put(directory.getName(), filesOfDirectory);
        }
    }
}
