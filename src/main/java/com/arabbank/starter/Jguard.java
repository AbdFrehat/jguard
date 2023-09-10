package com.arabbank.starter;

import com.arabbank.executor.FunctionExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jguard {
    private static final Logger logger = LoggerFactory.getLogger(Jguard.class);
    private static final FunctionExecutor executor = new FunctionExecutor();


    private Jguard() {
    }

    public static void start() {
        executor.execute();
        final ValidationExecutor validationExecutor = new ValidationExecutor();
        validationExecutor.execute();

    }

    static {
        String logo = LogoCreator.createLogo();
        logger.info(logo);
    }
}
