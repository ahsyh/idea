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

    private void doWork() throws InterruptedException {
        TetrisFrame frame = new TetrisFrame(controller, keyHandler, uiContent);
        frame.init();
    }

    @Override
    public void run() {
        try {
            doWork();
        } catch (InterruptedException e) {
            System.out.print("meet with interruption: " + e);
        }
    }
}
