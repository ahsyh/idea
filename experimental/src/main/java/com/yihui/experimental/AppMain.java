package com.yihui.experimental;

import com.yihui.experimental.rxjava.Case003;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public final class AppMain {
    /**
     * Logger.
     */
    public static Logger logger = LoggerFactory.getLogger("Ex:");

    private AppMain() {
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

    /**
     *
     * @param args a
     */
    public static void main(final String[] args) {
        //Case001.getInstance().test(1,2,3);
        Case003 c = new Case003();
        c.test();
    }

}
