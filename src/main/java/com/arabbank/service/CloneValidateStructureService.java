package com.arabbank.service;

import com.arabbank.model.DirectoryRequirement;
import com.arabbank.model.ValidationResult;

import java.util.List;

public interface CloneValidateStructureService {
    ValidationResult cloneRepositoryAndValidateFolderStructure(String persistPath, List<DirectoryRequirement> directoryRequirements);
}
