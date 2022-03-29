package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.RotateDirection;
import com.yihui.tetris.util.ContentUtil;
import lombok.Getter;

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

    public BrickImpl(long[] c, int w, int h, int index, int rotation) {
        init(c, w, h);
        this.index = index;
        this.rotation = rotation;
    }

    @Override
    public void init(long[] c, int w, int h) {
        this.width = w;
        this.height = h;

        this.content = new long[h];
        System.arraycopy(c, 0, this.content, 0, h);
    }

    @Override
    public void rotate(RotateDirection r) {
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
        this.rotation ++;
    }

    @Override
    public Brick getRotate(RotateDirection r) {
        Brick b = new BrickImpl(content, width, height, index, rotation);
        b.rotate(r);
        return b;
    }
}
