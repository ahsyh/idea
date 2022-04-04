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
     * @param args a
     */
    public static void main(final String[] args) {
        //Case001.getInstance().test(1,2,3);
        //Case002.test();
        Case003.test();
    }

}
