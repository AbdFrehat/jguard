package com.arabbank.validator;

import com.arabbank.exception.FileExistenceValidationException;
import com.arabbank.exception.ProjectValidationException;
import com.arabbank.model.ApplicationYaml;
import com.arabbank.model.Project;
import com.arabbank.provider.FilesProvider;

public class ProjectValidator {

    private FileExistenceValidator fileExistenceValidator;
    private String projectPersistPath;
    private Project project;
    private ApplicationYaml applicationYaml;
    
    public ProjectValidator(FilesProvider filesProvider, String projectPersistPath, Project project, ApplicationYaml applicationYaml) {
        this.fileExistenceValidator = new FileExistenceValidator(filesProvider, projectPersistPath);
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
        if(!projectValidationException.getExceptions().isEmpty()) {
            throw projectValidationException;
        }
    }
}
