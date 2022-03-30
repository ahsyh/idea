package com.yihui.tetris.impl;

import com.yihui.life.util.ContentUtil;
import org.junit.jupiter.api.Test;

import static com.yihui.life.AppMain.logger;

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
    }
}
