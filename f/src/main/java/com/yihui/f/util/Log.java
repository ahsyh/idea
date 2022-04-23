package com.yihui.f.util;

import static com.yihui.f.AppMain.logger;

public final class Log {
    public static void e(String msg) {
        logger.warn(msg);
    }
    public static void i(String msg) {
        logger.info(msg);
    }
}
