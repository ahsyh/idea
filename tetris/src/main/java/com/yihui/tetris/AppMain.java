package com.yihui.tetris;

import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.Speed;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.dagger.GeneralComponent;
import com.yihui.tetris.dagger.DaggerGeneralComponent;
import com.yihui.tetris.threads.EventListenerManager;
import com.yihui.tetris.threads.UIThread;

import javax.inject.Inject;
import java.util.concurrent.ThreadPoolExecutor;

public class AppMain {
    @Inject protected Controller controller;
    @Inject protected UIContent uiContent;
    @Inject protected KeyHandler keyHandler;
    @Inject protected ThreadPoolExecutor executor;

    private AppMain() {
        GeneralComponent component = DaggerGeneralComponent.builder().build();
        component.inject(this);
    }

    private void run() {
        controller.setSpeed(Speed.LEVEL1);
        (new UIThread(controller, keyHandler, uiContent)).run();
        controller.init();
        controller.start();
        System.out.println("main controller started, " + System.currentTimeMillis());
    }

    public static void main(String args[]) {
        AppMain main = new AppMain();
        main.run();
    }
}
