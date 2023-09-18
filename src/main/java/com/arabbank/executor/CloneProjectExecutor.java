package com.arabbank.executor;

import com.arabbank.exception.GitCloneException;
import com.arabbank.process.CloneProjectProcess;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CloneProjectExecutor implements CloneProjectProcess {
    private static final Logger logger = LoggerFactory.getLogger(CloneProjectExecutor.class);

    @Override
    public void clone(String repositoryUrl, String persistPath) {
        try {
            logger.info("Cloning repository from [{}], persisting to [{}]", repositoryUrl, persistPath);
            Git call = Git.cloneRepository()
                    .setURI(repositoryUrl)
                    .setDirectory(new File(persistPath))
                    .call();
            call.close();
            logger.info("Cloned repository into [{}]", persistPath);
        } catch (GitAPIException e) {
            logger.error("There was an issue while cloning repository, please try again");
            throw new GitCloneException(e.getMessage());
        }
    }
}
