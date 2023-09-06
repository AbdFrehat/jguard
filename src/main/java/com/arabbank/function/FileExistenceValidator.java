package com.arabbank.function;

import java.io.File;

@FunctionalInterface
public interface FileExistenceValidator {
    boolean validate(File file);
}
