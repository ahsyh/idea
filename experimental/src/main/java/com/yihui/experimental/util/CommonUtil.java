package com.yihui.experimental.util;

import static com.yihui.experimental.AppMain.logger;

public class CommonUtil {
    private CommonUtil() {

    }

    public static void sleep(long ms) {
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            logger.warn("get interruption " + e);
        }
    }

    /**
     *
     * @param run r
     * @param caseName c
     */
    public static void runCase(final Runnable run, final String caseName) {
        logger.warn("Run case " + caseName + ", start...");
        run.run();
        logger.warn("Run case " + caseName + ", end.");
    }
}
