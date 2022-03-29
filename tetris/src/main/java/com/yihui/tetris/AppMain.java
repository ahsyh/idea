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

public class AppMain {
    @Inject protected Controller controller;
    @Inject protected UIContent uiContent;
    @Inject protected KeyHandler keyHandler;

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

    public static void main(String args[]) {
        AppMain main = new AppMain();
        main.run();
    }
}
