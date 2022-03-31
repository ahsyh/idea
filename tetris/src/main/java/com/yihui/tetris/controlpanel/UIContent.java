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
    private int score;

    /**
     *
     */
    public void init() {
        score = 0;
        getPanel().reset();
        setNext(BrickUtil.getRandomBrick());
        nextStep();
    }

    private static final int MAX_BRICK_HEIGHT = 4;
    /**
     *
     */
    public void nextStep() {
        setCurrent(getNext());
        setNext(BrickUtil.getRandomBrick());
        setBrickPositionX(PANEL_WIDTH / 2 - 2);
        setBrickPositionY(MAX_BRICK_HEIGHT - current.getHeight());
    }

    private static final int BONUS_SCORE_1 = 10;
    private static final int BONUS_SCORE_2 = 30;
    private static final int BONUS_SCORE_3 = 50;
    private static final int BONUS_SCORE_4 = 80;

    private static final int LINE_NUMBER_1 = 1;
    private static final int LINE_NUMBER_2 = 2;
    private static final int LINE_NUMBER_3 = 3;
    private static final int LINE_NUMBER_4 = 4;

    /**
     *
     * @param cleanLines cleared line number
     */
    public void updateScore(final int cleanLines) {
        switch (cleanLines) {
            case LINE_NUMBER_1:
                score += BONUS_SCORE_1;
                break;
            case LINE_NUMBER_2:
                score += BONUS_SCORE_2;
                break;
            case LINE_NUMBER_3:
                score += BONUS_SCORE_3;
                break;
            case LINE_NUMBER_4:
                score += BONUS_SCORE_4;
                break;
            default:
                break;
        }
    }
}
