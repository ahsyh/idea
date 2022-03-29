package com.yihui.tetris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.Speed;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.dagger.GeneralComponent;
import com.yihui.tetris.dagger.DaggerGeneralComponent;
import com.yihui.tetris.threads.UIThread;

import javax.inject.Inject;

/**
 * AppMain for Tetris.
 */
public final class AppMain {
    /**
     * controller.
     */
    @Inject protected Controller controller;
    /**
     * uiContent.
     */
    @Inject protected UIContent uiContent;
    /**
     * key handler.
     */
    @Inject protected KeyHandler keyHandler;

    /**
     * Logger.
     */
    public static Logger logger = LoggerFactory.getLogger("Tetris");

    private AppMain() {
        GeneralComponent component = DaggerGeneralComponent.builder().build();
        component.inject(this);
    }

    private void run() {
        (new UIThread(controller, keyHandler, uiContent)).run();

        controller.setSpeed(Speed.LEVEL1);
        controller.init();
        controller.start();

        logger.warn("main controller started, " + System.currentTimeMillis());
    }

    /**
     * main function.
     * @param args input
     */
    public static void main(final String[] args) {
        AppMain main = new AppMain();
        main.run();
    }
}
