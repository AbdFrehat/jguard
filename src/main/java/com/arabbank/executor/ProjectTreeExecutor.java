package com.arabbank.executor;

import com.arabbank.function.ProjectTreeFunction;
import com.arabbank.model.ProjectTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProjectTreeExecutor implements ProjectTreeFunction {
    private static final Logger logger = LoggerFactory.getLogger(ProjectTreeExecutor.class);

    @Override
    public ProjectTree scan(String path) {
        logger.info("Analyzing repository file tree from [{}]", path);
        ProjectTree projectTree = new ProjectTree(new ArrayList<>());
        scanDirectory(new File(path), projectTree);
        logger.info("Finished analyzing project tree");
        return projectTree;
    }

    private void scanDirectory(File directory, ProjectTree projectTree) {
        File[] files = directory.listFiles();
        if(files == null || files.length == 0) {
            throw new RuntimeException();
        }
        projectTree.projectDirectories().addAll(Arrays.asList(files));
    }
}
