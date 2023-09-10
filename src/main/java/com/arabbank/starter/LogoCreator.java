package com.arabbank.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LogoCreator {
    private static final Logger logger = LoggerFactory.getLogger(LogoCreator.class);

    private LogoCreator() {
    }

    public static String createLogo() {
        try (InputStream inputStream = LogoCreator.class.getClassLoader().getResourceAsStream("logo")) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (NullPointerException | IOException ioException) {
            logger.error("Logo not found");
            return "";
        }
    }
}
