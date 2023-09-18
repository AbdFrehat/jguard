package com.arabbank.model;

import lombok.Builder;

@Builder
public class ProjectModel {
    private String projectName;
    private String repositoryUrl;
    private PomFile pomFile;
    private ApplicationYaml yamlFile;
    private ProjectTree projectTree;
}
