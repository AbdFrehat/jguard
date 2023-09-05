package com.arabbank;

import com.arabbank.executor.GitCloneExecutor;
import com.arabbank.executor.ProjectTreeExecutor;
import com.arabbank.executor.ProjectTreeFilter;
import com.arabbank.starter.Jguard;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Jguard(new GitCloneExecutor(), new ProjectTreeExecutor(new ProjectTreeFilter(List.of(file -> !file.getName().equals(".git"))), List.of(file -> file.getName().matches(".*\\.java$"), File::isFile))).start("config.yml");
    }

}
