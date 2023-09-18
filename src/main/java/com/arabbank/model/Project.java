package com.arabbank.model;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    private String name;
    private String repositoryUrl;
    private List<String> filesToValidate;
    private List<String> propertiesToValidate;
}
