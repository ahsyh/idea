package com.yihui.tetris.controlpanel;

import lombok.Getter;

public enum Speed {
    LEVEL1(1, 1000),
    LEVEL2(2, 794),
    LEVEL3(3, 631),
    LEVEL4(4, 501),
    LEVEL5(5, 398),
    LEVEL6(6, 316),
    LEVEL7(7, 251),
    LEVEL8(8, 200),
    LEVEL9(9, 158),
    LEVEL10(10, 126);

    @Getter private final int level;
    @Getter private final int interval;

    Speed(int level, int interval) {
        this.level = level;
        this.interval = interval;
    }
}
