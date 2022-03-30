package com.yihui.life.impl;

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
        logger.warn("set running to false when stop");
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
        logger.warn("set running to " + running + " when pause");
        if (running) {
            next();
        }
    }

    @Override
    public void onSpeedUp() {
        speed = speed.next();
        logger.warn("new speed: " + speed + ", running: " + running);
    }

    @Override
    public void onSlowDown() {
        speed = speed.prev();
        logger.warn("new speed: " + speed + ", running: " + running);
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

    private static final int NOT_BAD_STATUS = 2;
    private static final int COMFORT_STATUS = 3;

    private int getNewStatus(final int liveCellNumberAround, final int lastStatus) {
        int newStatus;

        switch (liveCellNumberAround) {
            case NOT_BAD_STATUS:
                newStatus = lastStatus;
                break;
            case COMFORT_STATUS:
                newStatus = 1;
                break;
            default:
                newStatus = 0;
                break;
        }

        return newStatus;
    }

    private void updatePosition(
            @NonNull final long[] newContent,
            @NonNull final long[] oldContent,
            final int x, final int y) {
        int lastStatus = ContentUtil.getBitAtPosition(oldContent, x, y);
        int liveCellNumberAround = ContentUtil.getLiveCellAround(oldContent, x, y);
        ContentUtil.setBitAtPosition(newContent, x, y, getNewStatus(liveCellNumberAround, lastStatus));
    }
}
