package com.yihui.tetris.controlpanel;

import lombok.Data;

@Data
public class UIContent {
    private Panel panel;
    private Brick current;
    private int brickPositionX;
    private int brickPositionY;
    private Brick next;
    private boolean running;
}
