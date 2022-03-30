package com.yihui.life.threads;

import com.yihui.life.controlpanel.Controller;
import com.yihui.life.controlpanel.KeyHandler;
import com.yihui.life.controlpanel.UIContent;
import com.yihui.life.ui.LifeFrame;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@RequiredArgsConstructor
public final class UIThread implements Runnable {
    @NonNull private final Controller controller;
    @NonNull private final KeyHandler keyHandler;
    @NonNull private final UIContent uiContent;

    @Override
    public void run() {
        LifeFrame frame = new LifeFrame(controller, keyHandler, uiContent);
        frame.init();
        frame.display();
    }
}
