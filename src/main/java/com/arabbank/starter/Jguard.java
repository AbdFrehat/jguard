package com.arabbank.starter;

import com.arabbank.provider.YamlParser;

public class Jguard {
    public static void start() {
        displayLogo();
        new YamlParser();
    }

    private static void displayLogo() {
        System.out.println(LogoCreator.createLogo());
    }
}
