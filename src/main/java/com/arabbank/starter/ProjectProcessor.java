package com.arabbank.starter;

import static java.io.File.*;

import java.io.File;
import java.io.IOException;

import com.arabbank.executor.CloneProjectExecutor;
import com.arabbank.function.ParseYamlFunction;
import com.arabbank.model.ApplicationYaml;
import com.arabbank.model.Project;
import com.arabbank.process.CloneProjectProcess;
import com.arabbank.process.ParseApplicationYamlProcess;
import com.arabbank.process.ParsePomProcess;
import com.arabbank.process.ReadTreeProcess;
import com.arabbank.provider.FilesProvider;
import com.arabbank.validator.ProjectValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;

public class ProjectProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ProjectProcessor.class);
    private final CloneProjectProcess cloneProjectProcess;
    private final ReadTreeProcess readTreeProcess;
    private final FilesProvider filesProvider;
    private final ParseYamlFunction parseYamlFunction;
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
        if(makeProjectDir(projectPersistPath)) {
            cloneProjectProcess.clone(project.getRepositoryUrl(), projectPersistPath);
            readTreeProcess.process(projectPersistPath);
            parseApplicationYamlProcess.process();
            parsePomProcess.parse();
            ProjectValidator projectValidator = new ProjectValidator(filesProvider, persistPath, project);
            projectValidator.validate();
        } else {
            logger.error("{} is skipped, unable to created directory to clone in it", project.getName());
        }
    }

    private String getProjectPersistPath(Project project, String persistPath) {
        return persistPath +
                separator +
                project.getName();
    }

    private boolean makeProjectDir(String persistencePath) {
        return new File(persistencePath).mkdir();
    }
}
