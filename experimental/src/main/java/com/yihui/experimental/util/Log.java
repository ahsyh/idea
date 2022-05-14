package com.yihui.experimental.util;

import static com.yihui.experimental.AppMain.logger;

public final class Log {
    private Log() {
    }

    public static void e(String message) {
        logger.warn("Default" + " " + message);
    }
    public static void e(String tag, String message) {
        logger.warn(tag + " " + message);
    }
}
