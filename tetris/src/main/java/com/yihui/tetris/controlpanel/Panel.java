package com.yihui.tetris.controlpanel;

public interface Panel {
    void init(int w, int h);
    int getWidth();
    int getHeight();
    long[] getContent();
    void cleanFullLine();
    void reset();
}
