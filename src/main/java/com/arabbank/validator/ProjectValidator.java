package com.arabbank.validator;

import com.arabbank.exception.ApplicationYamlValidationException;
import com.arabbank.exception.FileExistenceValidationException;
import com.arabbank.exception.ProjectValidationException;
import com.arabbank.executor.ParseApplicationYamlExecutor;
import com.arabbank.model.Project;
import com.arabbank.provider.FilesProvider;

public class ProjectValidator {

    private final FileExistenceValidator fileExistenceValidator;
    private final ApplicationYamlPropertiesValidator applicationYamlPropertiesValidator;
    private final VersionValidator versionValidator;
    private final String projectPersistPath;
    private final Project project;

    public ProjectValidator(FilesProvider filesProvider,
                            String projectPersistPath,
                            Project project) {
        this.applicationYamlPropertiesValidator = new ApplicationYamlPropertiesValidator(ParseApplicationYamlExecutor.applicationYaml);
        this.fileExistenceValidator = new FileExistenceValidator(filesProvider, projectPersistPath);
        this.versionValidator = new VersionValidator();
        this.projectPersistPath = projectPersistPath;
        this.project = project;
    }

    public void validate() throws ProjectValidationException {
        ProjectValidationException projectValidationException = new ProjectValidationException(String.format("Project %s failed", projectPersistPath));
        try {
            this.fileExistenceValidator.validate(project.getFilesToValidate());
        } catch (FileExistenceValidationException fileExistenceValidationException) {
            projectValidationException.addException(fileExistenceValidationException);
        }
        try {
            this.applicationYamlPropertiesValidator.validate(project.getPropertiesToValidate());
        } catch (ApplicationYamlValidationException applicationYamlValidationException) {
            projectValidationException.addException(applicationYamlValidationException);
        }
        versionValidator.validate();
        if (!projectValidationException.getExceptions().isEmpty()) {
            throw projectValidationException;
        }
    }
}
