package com.arabbank.starter;

import static java.io.File.*;

import java.io.File;

import com.arabbank.function.ParseYamlFunction;
import com.arabbank.model.ApplicationYaml;
import com.arabbank.model.Project;
import com.arabbank.process.CloneProjectProcess;
import com.arabbank.process.ReadTreeProcess;
import com.arabbank.provider.FilesProvider;
import com.arabbank.validator.ProjectValidator;

public class ProjectProcessor {

    private CloneProjectProcess cloneProjectProcess;
    private ReadTreeProcess readTreeProcess;
    private FilesProvider filesProvider;
    private ParseYamlFunction parseYamlFunction;
    private ApplicationYaml applicationYaml;

    public ProjectProcessor(CloneProjectProcess cloneProjectProcess, ReadTreeProcess readTreeProcess,
            ParseYamlFunction parseYamlFunction, FilesProvider filesProvider) {
        this.cloneProjectProcess = cloneProjectProcess;
        this.readTreeProcess = readTreeProcess;
        this.parseYamlFunction = parseYamlFunction;
        this.filesProvider = filesProvider;
    }

    public void run(Project project, String persistPath) {
        String projectPersistPath = getProjectPersistPath(project, persistPath);
        makeProjectDir(projectPersistPath);
        cloneProjectProcess.clone(project.getRepositoryUrl(), projectPersistPath);
        readTreeProcess.process(projectPersistPath);
        this.applicationYaml = parseYamlFunction.parse(filesProvider
                .provide("\\w+\\.(yaml|yml)$")
                .get(0)
                .getAbsolutePath());
        ProjectValidator projectValidator = new ProjectValidator(filesProvider, persistPath, project, applicationYaml);
        projectValidator.validate();

    }

    private String getProjectPersistPath(Project project, String persistPath) {
        return new StringBuilder(persistPath)
                .append(separator)
                .append(project.getName())
                .toString();
    }

    private void makeProjectDir(String persistancePath) {
        new File(persistancePath).mkdir();
    }
}
