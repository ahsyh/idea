package com.yihui.life;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihui.life.controlpanel.Controller;
import com.yihui.life.controlpanel.KeyHandler;
import com.yihui.life.controlpanel.Speed;
import com.yihui.life.controlpanel.UIContent;
import com.yihui.life.dagger.GeneralComponent;
import com.yihui.life.dagger.DaggerGeneralComponent;
import com.yihui.life.threads.UIThread;

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
