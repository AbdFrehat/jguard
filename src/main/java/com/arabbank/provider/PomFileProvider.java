package com.arabbank.provider;

import com.arabbank.executor.PomFileExecutor;
import com.arabbank.function.PomFileFunction;
import com.arabbank.model.PomFile;

public class PomFileProvider {
    private final PomFileFunction pomFileFunction;
    private final PomFile pomFile;

    public PomFileProvider() {
        this.pomFileFunction = new PomFileExecutor();
        this.pomFile = pomFileFunction.readPomFile();
    }

    public String provide(String tagName) {
        return this.pomFile.tags().get(tagName);
    }
}
