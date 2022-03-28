package com.yihui.tetris.threads;

import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.ui.TetrisFrame;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UIThread implements Runnable {
    @NonNull private final Controller controller;
    @NonNull private final KeyHandler keyHandler;
    @NonNull private final UIContent uiContent;

    @Override
    public void run() {
        TetrisFrame frame = new TetrisFrame(controller, keyHandler, uiContent);
        frame.init();
        frame.display();
    }
}
