package com.yihui.tetris.controlpanel;

import com.yihui.tetris.util.BrickUtil;
import lombok.Data;

import static com.yihui.tetris.Constants.PANEL_WIDTH;

/**
 *
 */
@Data
public final class UIContent {
    private Panel panel;
    private Brick current;
    private int brickPositionX;
    private int brickPositionY;
    private Brick next;
    private boolean running;

    /**
     *
     */
    public void init() {
        getPanel().reset();
        setNext(BrickUtil.getRandomBrick());
        nextStep();
    }

    /**
     *
     */
    public void nextStep() {
        setCurrent(getNext());
        setNext(BrickUtil.getRandomBrick());
        setBrickPositionX(PANEL_WIDTH / 2 - 2);
        setBrickPositionY(0);
    }
}
