package com.yihui.f;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.yihui.f.questions.QSort.test;

/**
 * Application main.
 */
public final class AppMain {
    private AppMain() {
    }

    /**
     * Logger.
     */
    public static Logger logger = LoggerFactory.getLogger("f");

    /**
     * Main method.
     * @param args input.
     */
    public static void main(final String[] args) {
        test();
    }
}
