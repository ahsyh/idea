package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.util.ContentUtil;
import lombok.Getter;

/**
 *
 */
public class BrickImpl implements Brick {

    @Getter
    private long[] content = null;
    @Getter
    private int width;
    @Getter
    private int height;

    @Getter
    private int index;
    @Getter
    private int rotation;

    /**
     *
     * @param c content
     * @param w width
     * @param h height
     * @param i index
     * @param r rotation
     */
    public BrickImpl(final long[] c, final int w, final int h,
                     final int i, final int r) {
        init(c, w, h);
        this.index = i;
        this.rotation = r;
    }

    /**
     *
     * @param c content
     * @param w width
     * @param h height
     */
    @Override
    public void init(final long[] c, final int w, final int h) {
        this.width = w;
        this.height = h;

        this.content = new long[h];
        System.arraycopy(c, 0, this.content, 0, h);
    }

    /**
     *
     * @param r rotation
     */
    @Override
    public void rotate(final RotateDirection r) {
        int w = height;
        int h = width;
        long[] newContent = new long[h];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int bit = ContentUtil.getBitAtPosition(content, j, i);
                switch (r) {
                    case ClockWise:
                        ContentUtil.setBitAtPosition(newContent, height - i - 1, j, bit);
                        break;
                    case CounterClockWise:
                        ContentUtil.setBitAtPosition(newContent, i, width - j - 1, bit);
                        break;
                    default:
                        break;
                }
            }
        }

        this.content = newContent;
        this.width = w;
        this.height = h;
        this.rotation++;
    }

    /**
     *
     * @param r rotation
     *
     * @return new brick
     */
    @Override
    public Brick getRotate(final RotateDirection r) {
        Brick b = new BrickImpl(content, width, height, index, rotation);
        b.rotate(r);
        return b;
    }

    /**
     *
     * @param x x
     * @param y y
     * @return 0
     */
    @Override
    public int getBitAtPosition(final int x, final int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return -1;
        }

        return ContentUtil.getBitAtPosition(content, x, y);
    }
}
