package com.arabbank.process;

@FunctionalInterface
public interface CloneProjectProcess {
    void clone(String repositoryUrl, String persistPath);
}
