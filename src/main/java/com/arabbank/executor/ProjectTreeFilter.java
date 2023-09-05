package com.arabbank.executor;

import com.arabbank.function.ProjectFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class ProjectTreeFilter implements FileFilter {
    private final List<ProjectFileFilter> projectFileFilters;

    public ProjectTreeFilter(List<ProjectFileFilter> projectFileFilters) {
        this.projectFileFilters = projectFileFilters;
    }

    @Override
    public boolean accept(File pathname) {

        return projectFileFilters.stream().allMatch(projectFileFilter -> projectFileFilter.filter(pathname));
    }
}
