package com.yihui.life.impl;

import com.yihui.life.Constants;
import com.yihui.life.controlpanel.Panel;
import lombok.Getter;

import static com.yihui.life.AppMain.logger;

/**
 *
 */
@Getter
public final class PanelImpl implements Panel {
    private long[] content;
    private int width;
    private int height;

    private static long fullLine;
    static final long BIT_ONE = 0b1;
    static {
        fullLine = 0;
        long bit = BIT_ONE;
        for (int i = 0; i < Constants.PANEL_WIDTH; i++) {
            fullLine |= bit;
            bit <<= 1;
        }
    }

    @Override
    public void init(final int w, final int h) {
        this.width = w;
        this.height = h;
        this.content = new long[h];
    }

    private static final long BIG_ENOUGH_BITS = 63;

    @Override
    public void reset() {
        for (int i = 0; i < height; i++) {
            this.content[i] = ((long) (Math.random() * ((long) 1 << BIG_ENOUGH_BITS))) & fullLine;
            logger.warn("PanelImpl reset panel, line " + i + ", data: " + this.content[i]);
        }
    }

}
