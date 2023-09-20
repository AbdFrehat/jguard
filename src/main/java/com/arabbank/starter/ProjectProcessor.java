package com.arabbank.starter;

import com.arabbank.model.Project;
import com.arabbank.process.CloneProjectProcess;
import com.arabbank.process.ParseApplicationYamlProcess;
import com.arabbank.process.ParsePomProcess;
import com.arabbank.process.ReadTreeProcess;
import com.arabbank.provider.FilesProvider;
import com.arabbank.validator.ProjectValidator;

import java.io.File;

import static java.io.File.separator;

public class ProjectProcessor {

    private final CloneProjectProcess cloneProjectProcess;
    private final ReadTreeProcess readTreeProcess;
    private final FilesProvider filesProvider;
    private final ParseApplicationYamlProcess parseApplicationYamlProcess;
    private final ParsePomProcess parsePomProcess;

    public ProjectProcessor(CloneProjectProcess cloneProjectProcess,
                            ReadTreeProcess readTreeProcess,
                            FilesProvider filesProvider,
                            ParseApplicationYamlProcess parseApplicationYamlProcess, ParsePomProcess parsePomProcess) {
        this.cloneProjectProcess = cloneProjectProcess;
        this.readTreeProcess = readTreeProcess;
        this.filesProvider = filesProvider;
        this.parseApplicationYamlProcess = parseApplicationYamlProcess;
        this.parsePomProcess = parsePomProcess;
    }

    public void run(Project project, String persistPath) {
        String projectPersistPath = getProjectPersistPath(project, persistPath);
        makeProjectDir(projectPersistPath);
        cloneProjectProcess.clone(project.getRepositoryUrl(), projectPersistPath);
        readTreeProcess.process(projectPersistPath);
        parseApplicationYamlProcess.process();
        parsePomProcess.parse();
        ProjectValidator projectValidator = new ProjectValidator(filesProvider, persistPath, project);
        projectValidator.validate();
    }

    private String getProjectPersistPath(Project project, String persistPath) {
        return persistPath +
                separator +
                project.getName();
    }

    private void makeProjectDir(String persistencePath) {
        new File(persistencePath).mkdir();
    }
}
