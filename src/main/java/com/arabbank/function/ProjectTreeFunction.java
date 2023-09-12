package com.arabbank.function;

import com.arabbank.model.ProjectTree;

@FunctionalInterface
public interface ProjectTreeFunction {
    ProjectTree scan(String path);
}
