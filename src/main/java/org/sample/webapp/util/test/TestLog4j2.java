package org.sample.webapp.util.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j2 {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            LOGGER.info("Rolling file appender example...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}