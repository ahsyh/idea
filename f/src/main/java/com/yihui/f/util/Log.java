package com.yihui.f.util;

import static com.yihui.f.AppMain.logger;

/**
 * ..
 */
public final class Log {
    private Log() {
    }

    /**
     * e log.
     * @param msg ...
     */
    public static void e(final String msg) {
        logger.warn(msg);
    }

    /**
     * i log.
     * @param msg ...
     */
    public static void i(final String msg) {
        logger.info(msg);
    }
}
