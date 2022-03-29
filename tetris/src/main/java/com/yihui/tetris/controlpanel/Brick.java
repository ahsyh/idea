package com.yihui.tetris.controlpanel;

public interface Brick {
    void init(long[] c, int w, int h);
    long[] getContent();
    int getWidth();
    int getHeight();
    int getIndex();
    int getRotation();
    void rotate(RotateDirection r);
    Brick getRotate(RotateDirection r);
}
