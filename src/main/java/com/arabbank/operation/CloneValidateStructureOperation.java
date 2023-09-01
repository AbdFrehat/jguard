package com.arabbank.operation;

import com.arabbank.function.GitCloneFunction;
import com.arabbank.function.YamlConfigurationProvider;
import com.arabbank.service.CloneValidateStructureService;
import com.arabbank.service.model.DirectoryRequirement;
import com.arabbank.service.model.ValidationResult;

import java.util.List;

public class CloneValidateStructureOperation implements CloneValidateStructureService {
    private final GitCloneFunction gitCloneFunction;
    private final YamlConfigurationProvider yamlConfigurationProvider;

    public CloneValidateStructureOperation(GitCloneFunction gitCloneFunction, YamlConfigurationProvider yamlConfigurationProvider) {
        this.gitCloneFunction = gitCloneFunction;
        this.yamlConfigurationProvider = yamlConfigurationProvider;
    }

    @Override
    public ValidationResult cloneRepositoryAndValidateFolderStructure(
            String persistPath,
            List<DirectoryRequirement> directoryRequirements) {
        String repositoryUrl = yamlConfigurationProvider.provide("repositoryUrl");
        gitCloneFunction.cloneGitRepository(repositoryUrl, persistPath);
        return null;
    }
}
