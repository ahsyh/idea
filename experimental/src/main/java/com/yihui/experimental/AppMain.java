package com.yihui.experimental;

import com.yihui.experimental.optional.Case001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public final class AppMain {
    private AppMain() {
    }

    /**
     * Logger.
     */
    public static Logger logger = LoggerFactory.getLogger("Life");

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

    /**
     *
     * @param args a
     */
    public static void main(final String[] args) {
        Case001.main();
    }

}
