package com.arabbank.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LogoCreator {
    private static final Logger logger = LoggerFactory.getLogger(LogoCreator.class);

    public static String createLogo() {
        InputStream fileInputStream;
        try {
            fileInputStream = LogoCreator.class.getClassLoader().getResourceAsStream("logo");
            return new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (NullPointerException | IOException ioException) {
            System.out.println("Logo not found");
            return "";
        }
    }
}
