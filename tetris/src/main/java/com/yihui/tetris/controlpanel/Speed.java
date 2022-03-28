package com.yihui.tetris.controlpanel;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum Speed {
    LEVEL1(1, 1000L),
    LEVEL2(2, 794L),
    LEVEL3(3, 631L),
    LEVEL4(4, 501L),
    LEVEL5(5, 398L),
    LEVEL6(6, 316L),
    LEVEL7(7, 251L),
    LEVEL8(8, 200L),
    LEVEL9(9, 158L),
    LEVEL10(10, 126L);

    @Getter private final int level;
    @Getter private final long interval;

    Speed(int level, long interval) {
        this.level = level;
        this.interval = interval;
    }

    private static final Map<Integer, Speed> map = new HashMap<>();
    static {
        for (Speed s : Speed.values()) {
            map.put(s.getLevel(), s);
        }
    }

    public Speed next() {
        if (map.get(level + 1) == null) {
            return this;
        } else {
            return map.get(level + 1);
        }
    }

    public Speed prev() {
        if (map.get(level - 1) == null) {
            return this;
        } else {
            return map.get(level - 1);
        }
    }
}
