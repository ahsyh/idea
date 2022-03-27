package com.yihui.tetris.controlpanel;

public interface Brick {
    void init(long[] c, int w, int h, int variantNumber);
    long[] getContent();
    int getWidth();
    int getHeight();
    int getIndex();
    int getRotation();
    int getVariantNumber();
    void rotate(RotateDirection r);
    Brick getRotate(RotateDirection r);
}
