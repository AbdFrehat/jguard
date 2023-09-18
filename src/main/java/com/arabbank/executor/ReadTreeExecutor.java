package com.arabbank.executor;

import com.arabbank.model.ProjectTree;
import com.arabbank.model.enums.ConfigProps;
import com.arabbank.process.ReadTreeProcess;
import com.arabbank.provider.ConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadTreeExecutor implements ReadTreeProcess {
    private static final Logger logger = LoggerFactory.getLogger(ReadTreeExecutor.class);
    private final ConfigurationProvider configurationProvider;
    public static ProjectTree projectTree;

    public ReadTreeExecutor(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    @Override
    public void process() {
        String path = configurationProvider.provide(ConfigProps.PERSIST_PATH);
        logger.info("Analyzing repository file tree from [{}]", path);
        projectTree = new ProjectTree(new ArrayList<>());
        scanDirectory(new File(path), projectTree);
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
