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

import static com.yihui.tetris.Constants.PANEL_WIDTH;

@RequiredArgsConstructor
public class ControllerImpl implements Controller, KeyHandler {
    @NonNull private final UIContent uiContent;

    @Setter
    private UIEngine uiEngine;

    @Getter
    @Setter
    private Speed speed;

    @Override
    public void onTimer() {
        System.out.println("controll onTimer start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            if (ContentUtil.isMergeCauseConflict(uiContent.getPanel().getContent(),
                    uiContent.getCurrent(), x, y + 1)) {
                // touch bottom
                ContentUtil.mergeBrick(uiContent.getPanel().getContent(),
                        uiContent.getCurrent(), x, y);
                uiContent.getPanel().cleanFullLine();
                uiContent.setCurrent(uiContent.getNext());
                uiContent.setNext(BrickUtil.getRandomBrick());
                uiContent.setBrickPositionX(PANEL_WIDTH/2-2);
                uiContent.setBrickPositionY(0);
                System.out.println("controll onTimer, touch bottom. y: " + y);
            } else {
                uiContent.setBrickPositionY(y + 1);
                System.out.println("controll onTimer, set y: " + y);
            }
        }

        uiEngine.display();
        System.out.println("controll onTimer end, " + System.currentTimeMillis());
    }

    @Override
    public void onInit() {
        Brick curr = BrickUtil.getRandomBrick();
        Brick next = BrickUtil.getRandomBrick();

        synchronized (uiContent) {
            uiContent.getPanel().reset();
            uiContent.setCurrent(curr);
            uiContent.setNext(next);
            uiContent.setBrickPositionY(0);
            uiContent.setBrickPositionX(PANEL_WIDTH/2-2);
        }

        //uiEngine.display();
        System.out.println(Thread.currentThread() + "controll onInit end, current index: " + curr.getIndex()
                + ", rotation: " + curr.getRotation() + ", time: " + System.currentTimeMillis());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

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
            x--;
            if (ContentUtil.isMergeCauseConflict(uiContent.getPanel().getContent(), uiContent.getCurrent(), x, y)) {
                // touch bottom
                System.out.println("controll onLeft, touch edge. x: " + x);
            } else {
                uiContent.setBrickPositionX(x);
                System.out.println("controll onLeft, set x: " + x);
            }
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
            x++;
            if (ContentUtil.isMergeCauseConflict(uiContent.getPanel().getContent(), uiContent.getCurrent(), x, y)) {
                // touch bottom
                System.out.println("controll onRight, touch edge. x: " + x);
            } else {
                uiContent.setBrickPositionX(x);
                System.out.println("controll onRight, set x: " + x);
            }
        }

        uiEngine.display();
        System.out.println("controll onRight end, " + System.currentTimeMillis());    }

    @Override
    public void onBottom() {
        System.out.println("controll onBottom start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();
            do {
                if (ContentUtil.isMergeCauseConflict(uiContent.getPanel().getContent(),
                        uiContent.getCurrent(), x, y + 1)) {
                    // touch bottom
                    ContentUtil.mergeBrick(uiContent.getPanel().getContent(),
                            uiContent.getCurrent(), x, y);
                    uiContent.getPanel().cleanFullLine();
                    uiContent.setCurrent(uiContent.getNext());
                    uiContent.setNext(BrickUtil.getRandomBrick());
                    uiContent.setBrickPositionX(PANEL_WIDTH/2-2);
                    uiContent.setBrickPositionY(0);
                    System.out.println("controll onBottom, touch bottom. y: " + y);
                    break;
                } else {
                    y++;
                    System.out.println("controll onBottom, set y: " + y);
                }
            } while (true);
        }

        uiEngine.display();
        System.out.println("controll onTimer end, " + System.currentTimeMillis());

    }

    @Override
    public void onRotateCW() {
        System.out.println("controll onRotateCW start, " + System.currentTimeMillis());
        synchronized (uiContent) {
            int x = uiContent.getBrickPositionX();
            int y = uiContent.getBrickPositionY();

            Brick b = uiContent.getCurrent().getRotate(RotateDirection.ClockWise);
            if (ContentUtil.isMergeCauseConflict(uiContent.getPanel().getContent(), b, x, y)) {
                // touch bottom
                System.out.println("controll onRotateCW, touch edge. x: " + x);
            } else {
                uiContent.getCurrent().rotate(RotateDirection.ClockWise);
                System.out.println("controll onRotateCW, cc rotated ");
            }
        }

        uiEngine.display();
        System.out.println("controll onRotateCW end, " + System.currentTimeMillis());
    }

    @Override
    public void onRotateCCW() {

    }
}
