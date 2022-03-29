package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.controlpanel.Speed;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.controlpanel.UIEngine;
import com.yihui.tetris.util.ContentUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.yihui.tetris.AppMain.logger;

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
                int x = uiContent.getBrickPositionX();
                int y = uiContent.getBrickPositionY();
                tryMove(x, y + 1, () -> {
                    finishCurrentBrick(x, y);
                });
            }

            if (isCurrentConflict()) {
                stop();
                logger.warn("controll onTimer end with game over, " + System.currentTimeMillis());
            } else {
                next();
            }
        }, "onTimer");
    }

    // implement KeyHandler interface
    @Override
    public void onLeft() {
        onUserAction(() -> {
            synchronized (uiContent) {
                int x = uiContent.getBrickPositionX();
                int y = uiContent.getBrickPositionY();
                tryMove(x - 1, y, () -> { });
            }
        }, "onLeft");
    }

    @Override
    public void onRight() {
        onUserAction(() -> {
            synchronized (uiContent) {
                int x = uiContent.getBrickPositionX();
                int y = uiContent.getBrickPositionY();
                tryMove(x + 1, y, () -> { });
            }
        }, "onRight");
    }

    @Override
    public void onBottom() {
        onUserAction(() -> {
            synchronized (uiContent) {
                int x = uiContent.getBrickPositionX();
                int y = uiContent.getBrickPositionY();
                while (tryMove(x, y + 1, () -> { })) {
                    y++;
                }
                finishCurrentBrick(x, y);
            }

            if (isCurrentConflict()) {
                stop();
                logger.warn("controll onTimer end with game over, " + System.currentTimeMillis());
            }
        }, "onBottom");
    }

    @Override
    public void onRotateCW() {
        onUserAction(() -> {
            synchronized (uiContent) {
                int x = uiContent.getBrickPositionX();
                int y = uiContent.getBrickPositionY();
                Brick b = uiContent.getCurrent().getRotate(RotateDirection.ClockWise);

                tryRotate(x, y, b);
            }
        }, "onRotateCW");
    }

    @Override
    public void onRotateCCW() {
        onUserAction(() -> {
            synchronized (uiContent) {
                int x = uiContent.getBrickPositionX();
                int y = uiContent.getBrickPositionY();
                Brick b = uiContent.getCurrent().getRotate(RotateDirection.CounterClockWise);

                tryRotate(x, y, b);
            }
        }, "onRotateCCW");
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

    private boolean isCurrentConflict() {
        return ContentUtil.isMergeCauseConflict(
                uiContent.getPanel().getContent(),
                uiContent.getCurrent(),
                uiContent.getBrickPositionX(),
                uiContent.getBrickPositionY());
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

    /**
     * Try move operation.
     * Do the move if no conflict.
     * Otherwise run the failed handling.
     * @return true if move succeed
     *         false if move would cause conflict
     */
    private boolean tryMove(final int x, final int y, final Runnable failedOperation) {
        if (ContentUtil.isMergeCauseConflict(uiContent.getPanel().getContent(),
                uiContent.getCurrent(), x, y)) {
            failedOperation.run();
            return false;
        } else {
            uiContent.setBrickPositionX(x);
            uiContent.setBrickPositionY(y);
            logger.warn("controll onTimer, set y: " + y);
            return true;
        }
    }

    private boolean tryRotate(final int x, final int y, final Brick b) {
        if (ContentUtil.isMergeCauseConflict(
                uiContent.getPanel().getContent(),
                b, x, y)) {
            return false;
        } else {
            uiContent.setCurrent(b);
            return true;
        }
    }

    private void finishCurrentBrick(final int x, final int y) {
        ContentUtil.mergeBrick(uiContent.getPanel().getContent(),
                uiContent.getCurrent(), x, y);
        uiContent.getPanel().cleanFullLine();
        uiContent.nextStep();
    }

    private void next() {
        executorService.schedule(this::onTimer, speed.getInterval(), TimeUnit.MILLISECONDS);
    }
}
