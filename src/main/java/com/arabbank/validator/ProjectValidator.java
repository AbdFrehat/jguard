package com.arabbank.validator;

import com.arabbank.exception.FileExistenceValidationException;
import com.arabbank.exception.ProjectValidationException;
import com.arabbank.exception.ResourceBundleValidationException;
import com.arabbank.model.ApplicationYaml;
import com.arabbank.model.Project;
import com.arabbank.provider.FilesProvider;

public class ProjectValidator {

    private final FileExistenceValidator fileExistenceValidator;

    private final ResourceBundleValidator resourceBundleValidator;
    private final String projectPersistPath;
    private final Project project;
    private ApplicationYaml applicationYaml;

    public ProjectValidator(FilesProvider filesProvider, String projectPersistPath, Project project, ApplicationYaml applicationYaml) {
        this.fileExistenceValidator = new FileExistenceValidator(filesProvider, projectPersistPath);
        this.resourceBundleValidator = new ResourceBundleValidator(filesProvider, projectPersistPath);
        this.projectPersistPath = projectPersistPath;
        this.project = project;
        this.applicationYaml = applicationYaml;
    }

    public void validate() throws ProjectValidationException {
        ProjectValidationException projectValidationException = new ProjectValidationException(String.format("Project %s failed", projectPersistPath));
        try {
            this.fileExistenceValidator.validate(project.getFilesToValidate());
        } catch (FileExistenceValidationException fileExistenceValidationException) {
            projectValidationException.addException(fileExistenceValidationException);
        }

        try {
            this.resourceBundleValidator.validate();
        } catch (ResourceBundleValidationException resourceBundleValidationException) {
            projectValidationException.addException(resourceBundleValidationException);
        }
        if (!projectValidationException.getExceptions().isEmpty()) {
            throw projectValidationException;
        }
    }
}
