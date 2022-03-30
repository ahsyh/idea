package com.yihui.life.impl;

import com.yihui.life.AppMain;
import com.yihui.life.controlpanel.Controller;
import com.yihui.life.controlpanel.KeyHandler;
import com.yihui.life.controlpanel.Speed;
import com.yihui.life.controlpanel.UIContent;
import com.yihui.life.controlpanel.UIEngine;
import com.yihui.life.util.ContentUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.yihui.life.AppMain.logger;
import static com.yihui.life.Constants.PANEL_HEIGHT;
import static com.yihui.life.Constants.PANEL_WIDTH;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
public final class ControllerImpl implements Controller, KeyHandler {
    @NonNull private final UIContent uiContent;
    private ScheduledExecutorService executorService;

    @Setter
    private UIEngine uiEngine;

    @Setter
    private Speed speed;

    private boolean running = false;

    // implement Controller interface
    @Override
    public void init() {
        uiEngine.reset();
    }

    @Override
    public void start() {
        executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(() -> {
            synchronized (uiContent) {
                uiContent.init();
                uiContent.setRunning(true);
            }
            running = true;
        }, Speed.LEVEL10.getInterval() / 2, TimeUnit.MILLISECONDS);

        executorService.schedule(this::onTimer, speed.getInterval(), TimeUnit.MILLISECONDS);

        logger.warn(Thread.currentThread() + "controll start end, "
                + System.currentTimeMillis());
    }

    @Override
    public void stop() {
        synchronized (uiContent) {
            uiContent.setRunning(false);
            uiContent.getPanel().reset();
        }
        running = false;
        executorService.shutdown();
    }


    @Override
    public void reset() {
        uiEngine.reset();
    }

    @Override
    public void onTimer() {
        onUserAction(() -> {
            synchronized (uiContent) {
                updateLife();
            }
            next();
        }, "onTimer");
    }

    @Override
    public void onReset() {
        reset();
        start();
    }

    @Override
    public void onPause() {
        running = !running;
        if (running) {
            next();
        }
    }

    @Override
    public void onSpeedUp() {
        speed = speed.next();
        logger.warn("new speed: " + speed);
    }

    @Override
    public void onSlowDown() {
        speed = speed.prev();
        logger.warn("new speed: " + speed);
    }

    private void onUserAction(final Runnable r, final String action) {
        if (!running) {
            return;
        }

        logger.warn("controller " + action + " start at " + System.currentTimeMillis());
        r.run();
        uiEngine.display();
        logger.warn("controller " + action + " end at " + System.currentTimeMillis());
    }

    private void next() {
        executorService.schedule(this::onTimer, speed.getInterval(), TimeUnit.MILLISECONDS);
    }

    private void updateLife() {
        long[] newContent = new long[PANEL_HEIGHT];

        for (int i = 0; i < PANEL_HEIGHT; i++) {
            for (int j = 0; j < PANEL_WIDTH; j++) {
                updatePosition(newContent, uiContent.getPanel().getContent(), j, i);
            }
        }

        ContentUtil.copyContent(newContent, uiContent.getPanel().getContent());
    }

    private void updatePosition(
            @NonNull final long[] newContent,
            @NonNull final long[] oldContent,
            final int x, final int y) {
        //obtain all cells around (x,y)
        int liveCellNumberAround = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i==0 && j==0) {
                    continue;
                }

                int result = ContentUtil.getBitAtPosition(oldContent, x + i, y + j);
                if (result > 0) {
                    liveCellNumberAround++;
                }
            }
        }

        int lastStatus = ContentUtil.getBitAtPosition(oldContent, x, y);
        int newStatus;

        switch (liveCellNumberAround) {
            case 2:
                newStatus = lastStatus;
                break;
            case 3:
                newStatus = 1;
                break;
            default:
                newStatus = 0;
                break;
        }

        ContentUtil.setBitAtPosition(newContent, x, y, newStatus);
    }


}
