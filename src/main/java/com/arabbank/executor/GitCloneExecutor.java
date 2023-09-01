package com.arabbank.executor;

import com.arabbank.function.GitCloneFunction;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class GitCloneExecutor implements GitCloneFunction {
    @Override
    public void cloneGitRepository(String repositoryUrl, String persistPath) {
        try {
            Git.cloneRepository()
                    .setURI(repositoryUrl)
                    .setDirectory(new File(persistPath))
                    .call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }
}
