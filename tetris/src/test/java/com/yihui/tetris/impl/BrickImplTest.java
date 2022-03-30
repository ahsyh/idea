package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.util.BrickUtil;
import com.yihui.tetris.util.ContentUtil;
import org.junit.jupiter.api.Test;

import static com.yihui.tetris.AppMain.logger;

/**
 *
 */
public class BrickImplTest {

    private static final int BIG_TEST_COUNT = 50;
    /**
     *
     */
    @Test
    public void testRotation() {
        for (int i = 0; i < BIG_TEST_COUNT; i++) {
            testRotationOnce();
        }
    }

    private void testRotationOnce() {
        // precondition
        Brick b = BrickUtil.getRandomBrick();
        logger.warn("origin brick:");
        ContentUtil.printContent(b.getContent(), b.getWidth(), b.getHeight());
        b.rotate(RotateDirection.ClockWise);
        logger.warn("rotated brick:");
        ContentUtil.printContent(b.getContent(), b.getWidth(), b.getHeight());
        logger.warn("---------------------------------------------");
    }
}
