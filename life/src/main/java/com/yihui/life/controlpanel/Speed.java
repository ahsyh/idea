package com.yihui.life.controlpanel;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Speed of game.
 */
public enum Speed {
    /**
     *
     */
    LEVEL1(1, 1000L),
    /**
     *
     */
    LEVEL2(2, 794L),
    /**
     *
     */
    LEVEL3(3, 631L),
    /**
     *
     */
    LEVEL4(4, 501L),
    /**
     *
     */
    LEVEL5(5, 398L),
    /**
     *
     */
    LEVEL6(6, 316L),
    /**
     *
     */
    LEVEL7(7, 251L),
    /**
     *
     */
    LEVEL8(8, 200L),
    /**
     *
     */
    LEVEL9(9, 158L),
    /**
     *
     */
    LEVEL10(10, 126L);

    @Getter private final int level;
    @Getter private final long interval;

    /**
     *
     */
    Speed(final int l, final long i) {
        this.level = l;
        this.interval = i;
    }

    private static final Map<Integer, Speed> MAP = new HashMap<>();
    static {
        for (final Speed s : Speed.values()) {
            MAP.put(s.getLevel(), s);
        }
    }

    /**
     *
     * @return next level
     */
    public Speed next() {
        if (MAP.get(level + 1) == null) {
            return this;
        } else {
            return MAP.get(level + 1);
        }
    }

    /**
     *
     * @return prev level
     */
    public Speed prev() {
        if (MAP.get(level - 1) == null) {
            return this;
        } else {
            return MAP.get(level - 1);
        }
    }
}
