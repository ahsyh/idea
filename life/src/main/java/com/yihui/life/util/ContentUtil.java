package com.yihui.life.util;

import com.yihui.life.AppMain;

import static com.yihui.life.Constants.PANEL_HEIGHT;
import static com.yihui.life.Constants.PANEL_WIDTH;

/**
 *
 */
public final class ContentUtil {

    private ContentUtil() {
    }

    /**
     *
     * @param content content
     * @param x x
     * @param y y
     * @return bit at postion
     */
    public static int getBitAtPosition(final long[] content, final int x, final int y) {
        if (x < 0 || x >= PANEL_WIDTH || y < 0 || y >= PANEL_HEIGHT) {
            return -1;
        }

        if ((content[y] & ((long) 0x1) << x) != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     *
     * @param content content
     * @param x x
     * @param y y
     * @param bit bit
     */
    public static void setBitAtPosition(final long[] content, final int x, final int y, final int bit) {
        long value;
        value = (bit == 0) ? ~(((long) 0x1) << x) : (((long) 0x1) << x);
        if (bit == 0) {
            content[y] &= value;
        } else {
            content[y] |= value;
        }
    }

    /**
     *
     * @param srcContent src
     * @param destContent dest
     */
    public static void copyContent(final long[] srcContent, final long[] destContent) {
        System.arraycopy(srcContent, 0, destContent, 0, PANEL_HEIGHT);
    }

    /**
     *
     * @param content content
     */
    public static void clearContent(final long[] content) {
        for (int i = 0; i < PANEL_HEIGHT; i++) {
            content[i] = 0;
        }
    }

    /**
     *
     * @param content c
     * @param width w
     * @param height h
     */
    public static void printContent(final long[] content, final int width, final int height) {
        for (int i = 0; i < height; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < width; j++) {
                if (getBitAtPosition(content, j, i) == 1) {
                    sb.append("X");
                } else {
                    sb.append("_");
                }
            }
            AppMain.logger.warn(sb.toString());
        }
    }
}
