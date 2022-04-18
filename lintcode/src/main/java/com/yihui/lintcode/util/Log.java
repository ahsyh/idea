package com.yihui.lintcode.util;

import static com.yihui.lintcode.AppMain.logger;

public final class Log {
    private Log() {
    }

    public static void e(String message) {
        logger.warn("LintCode: " + message);
    }
}
