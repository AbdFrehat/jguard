package com.arabbank.model;

import java.io.File;
import java.util.List;
import java.util.Map;

public record ProjectTree(String projectName, Map<String, List<File>> projectDirectories) {
}
