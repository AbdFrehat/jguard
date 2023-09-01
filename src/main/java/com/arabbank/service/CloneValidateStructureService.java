package com.arabbank.service;

import com.arabbank.service.model.DirectoryRequirement;
import com.arabbank.service.model.ValidationResult;

import java.util.List;

public interface CloneValidateStructureService {
    ValidationResult cloneRepositoryAndValidateFolderStructure(String persistPath, List<DirectoryRequirement> directoryRequirements);
}
