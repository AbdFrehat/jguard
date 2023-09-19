package com.arabbank.executor;

import com.arabbank.model.ProjectTree;
import com.arabbank.process.ReadTreeProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadTreeExecutor implements ReadTreeProcess {
    private static final Logger logger = LoggerFactory.getLogger(ReadTreeExecutor.class);
    public static ProjectTree projectTree;

    @Override
    public void process(String persistPath) {
        logger.info("Analyzing repository file tree from [{}]", persistPath);
        projectTree = new ProjectTree(new ArrayList<>());
        scanDirectory(new File(persistPath), projectTree);
        logger.info("Finished analyzing project tree");
    }

    private void scanDirectory(File directory, ProjectTree projectTree) {
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            throw new RuntimeException();
        }
        projectTree.projectDirectories().addAll(Arrays.asList(files));
    }
}
