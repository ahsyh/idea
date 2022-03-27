package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.controlpanel.Speed;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.controlpanel.UIEngine;
import com.yihui.tetris.util.BrickUtil;
import com.yihui.tetris.util.ContentUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.yihui.tetris.Constants.PANEL_WIDTH;

@RequiredArgsConstructor
public class ControllerImpl implements Controller, KeyHandler {
    @NonNull private final UIContent uiContent;
    private ScheduledExecutorService executorService;

    @Setter
    private UIEngine uiEngine;

    @Getter
    @Setter
    private Speed speed;

    private boolean running = false;

    private boolean isCurrentConflict() {
        return ContentUtil.isMergeCauseConflict(
                uiContent.getPanel().getContent(),
                uiContent.getCurrent(),
                uiContent.getBrickPositionX(),
                uiContent.getBrickPositionY());
    }
    /**
     * Try move operation.
     * Do the move if no conflict.
     * Otherwise run the failed handling.
     * @return true if move succeed
     *         false if move would cause conflict
     */
    private boolean tryMove(
            int x,
            int y,
            Runnable failedOperation) {
        if (ContentUtil.isMergeCauseConflict(uiContent.getPanel().getContent(),
                uiContent.getCurrent(), x, y)) {
            failedOperation.run();
            return false;
        } else {
            uiContent.setBrickPositionX(x);
            uiContent.setBrickPositionY(y);
            System.out.println("controll onTimer, set y: " + y);
            return true;
        }
    }

    @Override
    public void onInit() {
    }

    @Override
    public void start() {
        synchronized (uiContent) {
            uiContent.init();
            uiContent.setRunning(true);
        }
        running = true;
        uiEngine.reset();

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            onTimer();
        }, speed.getInterval(), speed.getInterval(), TimeUnit.MILLISECONDS);

        //uiEngine.display();
        System.out.println(Thread.currentThread() + "controll onInit end, current index: "
                + uiContent.getCurrent().getIndex() + ", rotation: "
                + uiContent.getCurrent().getRotation() + ", time: " + System.currentTimeMillis());

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

    }

    @Override
    public void onLeft() {
        System.out.println("controll onLeft start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            tryMove(x - 1, y, () -> { });
        }

        uiEngine.display();
        System.out.println("controll onLeft end, " + System.currentTimeMillis());
    }

    @Override
    public void onRight() {
        System.out.println("controll onRight start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            tryMove(x + 1, y, () -> { });
        }

        uiEngine.display();
        System.out.println("controll onRight end, " + System.currentTimeMillis());    }

    @Override
    public void onBottom() {
        System.out.println("controll onBottom start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            while (tryMove(x, y + 1, () -> {})) {
                y++;
            }

            finishCurrentBrick(x, y);
        }

        if (isCurrentConflict()) {
            stop();
            System.out.println("controll onTimer end with game over, " + System.currentTimeMillis());
        }

        uiEngine.display();
        System.out.println("controll onTimer end, " + System.currentTimeMillis());
    }

    @Override
    public void onTimer() {
        if (!running) return;

        System.out.println("controll onTimer start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            tryMove(x, y + 1, () -> {
                finishCurrentBrick(x, y);
            });
        }

        if (isCurrentConflict()) {
            stop();
            System.out.println("controll onTimer end with game over, " + System.currentTimeMillis());
        }

        uiEngine.display();

        System.out.println("controll onTimer end, " + System.currentTimeMillis());
    }

    private void finishCurrentBrick(int x, int y) {
        ContentUtil.mergeBrick(uiContent.getPanel().getContent(),
                uiContent.getCurrent(), x, y);
        uiContent.getPanel().cleanFullLine();
        uiContent.nextStep();
    }

    private boolean tryRotate(
            int x,
            int y,
            Brick b) {
        if (ContentUtil.isMergeCauseConflict(
                uiContent.getPanel().getContent(),
                b, x, y)) {
            return false;
        } else {
            uiContent.setCurrent(b);
            return true;
        }
    }

    @Override
    public void onRotateCW() {
        System.out.println("controll onRotateCW start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            Brick b = uiContent.getCurrent().getRotate(RotateDirection.ClockWise);

            tryRotate(x, y, b);
        }

        uiEngine.display();
        System.out.println("controll onRotateCW end, " + System.currentTimeMillis());
    }

    @Override
    public void onRotateCCW() {
        System.out.println("controll onRotateCW start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            Brick b = uiContent.getCurrent().getRotate(RotateDirection.CounterClockWise);

            tryRotate(x, y, b);
        }

        uiEngine.display();
        System.out.println("controll onRotateCW end, " + System.currentTimeMillis());
    }

    @Override
    public void onReset() {
        start();
    }
}
