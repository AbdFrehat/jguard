package com.arabbank.operation;

import com.arabbank.function.GitCloneFunction;
import com.arabbank.service.CloneValidateStructureService;
import com.arabbank.service.model.DirectoryRequirement;
import com.arabbank.service.model.ValidationResult;

import java.util.List;

public class CloneValidateStructureOperation implements CloneValidateStructureService {
    private final GitCloneFunction gitCloneFunction;

    public CloneValidateStructureOperation(GitCloneFunction gitCloneFunction) {
        this.gitCloneFunction = gitCloneFunction;
    }

    @Override
    public ValidationResult cloneRepositoryAndValidateFolderStructure(
            String repositoryUrl,
            String persistPath,
            List<DirectoryRequirement> directoryRequirements) {
        gitCloneFunction.cloneGitRepository(repositoryUrl, persistPath);
        return null;
    }
}
