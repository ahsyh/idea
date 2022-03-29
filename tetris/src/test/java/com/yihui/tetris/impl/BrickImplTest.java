package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.util.BrickUtil;
import com.yihui.tetris.util.ContentUtil;
import org.junit.jupiter.api.Test;

import static com.yihui.tetris.AppMain.logger;

public class BrickImplTest {
    @Test
    public void test_rotation() {
        for (int i = 0; i < 50; i++) {
            test_rotation_once();
        }
    }

    private void test_rotation_once() {
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
