package com.arabbank.function;

@FunctionalInterface
public interface GitCloneFunction {
    void cloneGitRepository(String repositoryUrl, String persistPath);
}
