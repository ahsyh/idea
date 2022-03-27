package com.yihui.tetris.util;

import com.yihui.tetris.controlpanel.Brick;

import static com.yihui.tetris.Constants.PANEL_HEIGHT;
import static com.yihui.tetris.Constants.PANEL_WIDTH;

public class ContentUtil {
    public static boolean isBitAtPositionEqual(long[] src, long[] dest, int x, int y) {
        int bitSrc = getBitAtPosition(src, x, y);
        int bitDest = getBitAtPosition(dest, x, y);

        return bitDest == bitSrc;
    }

    public static int getBitAtPosition(long[] content, int x, int y) {
        if ((content[y] & ((long)0x1) << x) != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void setBitAtPosition(long[] content, int x, int y, int bit) {
        long value;
        value = (bit == 0) ? ~(((long)0x1) << x) : (((long)0x1) << x);
        if (bit == 0) {
            content[y] &= value;
        } else {
            content[y] |= value;
        }
    }

    public static void copyContent(long[] srcContent, long[] destContent) {
        System.arraycopy(srcContent, 0, destContent, 0, PANEL_HEIGHT);
    }

    public static void clearContent(long[] content) {
        for (int i = 0; i< PANEL_HEIGHT; i++) {
            content[i] = 0;
        }
    }

    public static void printContent(long[] content, int width, int height) {
        for (int i = 0; i < height; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < width; j++) {
                if (getBitAtPosition(content, j, i) == 1) {
                    sb.append("X");
                } else {
                    sb.append("_");
                }
            }
            System.out.println(sb.toString());
        }
    }

    public static void mergeBrick(long[] content, Brick b, int brickPositionX, int brickPositionY) {
        for (int i = 0; i < b.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                int h = i + brickPositionY;
                int w = j + brickPositionX;

                if (h >= PANEL_HEIGHT || w >= PANEL_WIDTH) {
                    continue;
                }

                if (getBitAtPosition(b.getContent(), j, i) != 0) {
                    setBitAtPosition(content, w, h, 1);
                }
            }
        }
    }

    public static boolean isMergeCauseConflict(long[] content, Brick b, int brickPositionX, int brickPositionY) {
        if (brickPositionX < 0 || brickPositionY <0) {
            return true;
        }

        for (int i = 0; i < b.getHeight(); i++) {
            for (int j = 0; j < b.getWidth(); j++) {
                int h = i + brickPositionY;
                int w = j + brickPositionX;

                if (h >= PANEL_HEIGHT || w >= PANEL_WIDTH) {
                    return true;
                }

                if (getBitAtPosition(b.getContent(), j, i) != 0 &&
                    getBitAtPosition(content, w, h) != 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
