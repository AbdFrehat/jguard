package com.arabbank.function;

import java.io.File;

@FunctionalInterface
public interface ProjectFileFilter {
    boolean filter(File file);
}
