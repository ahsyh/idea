package com.yihui.tetris.controlpanel;

import lombok.Getter;

public enum Speed {
    LEVEL1(1),
    LEVEL2(2),
    LEVEL3(3);

    @Getter
    private final int speed;

    Speed(int speed) {
        this.speed = speed;
    }
}
