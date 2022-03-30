package com.yihui.tetris.util;

import com.yihui.tetris.controlpanel.Brick;
import org.junit.jupiter.api.Test;

import static com.yihui.tetris.AppMain.logger;
import static com.yihui.tetris.Constants.PANEL_HEIGHT;
import static com.yihui.tetris.Constants.PANEL_WIDTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class ContentUtilTest {

    private static final int BIG_TEST_COUNT = 100;
    /**
     *
     */
    @Test
    public void testMergeBrick() {
        long[] content = new long[PANEL_HEIGHT];
        for (int i = 0; i < BIG_TEST_COUNT; i++) {
            testMergeBrickOneStep(content);
        }

    }

    private static final int ONE_THOUSAND = 1000;

    private void testMergeBrickOneStep(final long[] content) {
        Brick b = BrickUtil.getRandomBrick();
        int x = ((int) (Math.random() * ONE_THOUSAND)) % PANEL_WIDTH;
        int y = ((int) (Math.random() * ONE_THOUSAND)) % PANEL_HEIGHT;
        logger.warn("Put brick to:" + x + ", " + y);
        logger.warn("origin brick:");
        ContentUtil.printContent(b.getContent(), b.getWidth(), b.getHeight());
        if (ContentUtil.isMergeCauseConflict(content, b, x, y)) {
            logger.warn("Found conflict, cannot merge");
            return;
        }
        ContentUtil.mergeBrick(content, b, x, y);
        logger.warn("panel after merge:");
        ContentUtil.printContent(content, PANEL_WIDTH, PANEL_HEIGHT);
    }

    /**
     *
     */
    @Test
    public void test_setBitAtPosition() {
        // precondition
        long[] content = new long[]{0, 0, 0};

        ContentUtil.setBitAtPosition(content, 0, 0, 1);
        ContentUtil.setBitAtPosition(content, 1, 0, 1);
        ContentUtil.setBitAtPosition(content, 2, 0, 1);
        ContentUtil.setBitAtPosition(content, 4, 1, 1);
        ContentUtil.setBitAtPosition(content, 5, 1, 1);
        ContentUtil.setBitAtPosition(content, 6, 1, 1);
        ContentUtil.setBitAtPosition(content, 8, 2, 1);
        ContentUtil.setBitAtPosition(content, 9, 2, 1);
        ContentUtil.setBitAtPosition(content, 10, 2, 1);

        assertEquals(0b111, content[0]);
        assertEquals(0b1110000, content[1]);
        assertEquals(0b11100000000, content[2]);

        ContentUtil.setBitAtPosition(content, 1, 0, 0);
        ContentUtil.setBitAtPosition(content, 5, 1, 0);
        ContentUtil.setBitAtPosition(content, 9, 2, 0);

        assertEquals(0b101, content[0]);
        assertEquals(0b1010000, content[1]);
        assertEquals(0b10100000000, content[2]);

    }

    /**
     *
     */
    @Test
    public void test_getBitAtPosition_positive() {
        // precondition
        long[] content = new long[] {
                0b111011,
                0b110010,
                0b011011
        };

        // run
        int result01 = ContentUtil.getBitAtPosition(content, 0, 0);
        int result02 = ContentUtil.getBitAtPosition(content, 0, 1);
        int result03 = ContentUtil.getBitAtPosition(content, 3, 0);
        int result04 = ContentUtil.getBitAtPosition(content, 3, 1);
        int result05 = ContentUtil.getBitAtPosition(content, 5, 2);

        // verification
        assertEquals(result01, 1);
        assertEquals(result02, 0);
        assertEquals(result03, 1);
        assertEquals(result04, 0);
        assertEquals(result05, 0);
    }
}
