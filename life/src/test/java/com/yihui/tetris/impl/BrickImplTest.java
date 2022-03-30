package com.yihui.tetris.impl;

import org.junit.jupiter.api.Test;

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
