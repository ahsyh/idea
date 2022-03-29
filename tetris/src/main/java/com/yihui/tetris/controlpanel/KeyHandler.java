package com.yihui.tetris.controlpanel;

public interface KeyHandler {
    // User Operation
    void onLeft();
    void onRight();
    void onBottom();
    void onRotateCW();
    void onRotateCCW();
    void onReset();
    void onPause();
    void onSpeedUp();
    void onSlowDown();
}
