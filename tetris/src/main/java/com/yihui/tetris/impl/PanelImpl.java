package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Panel;
import lombok.Getter;

import static com.yihui.tetris.Constants.PANEL_HEIGHT;
import static com.yihui.tetris.Constants.PANEL_WIDTH;

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
        for (int i = 0; i < PANEL_WIDTH; i++) {
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

    @Override
    public void cleanFullLine() {
        int checkLine = PANEL_HEIGHT - 1;
        int writeLine = PANEL_HEIGHT - 1;

        for (; checkLine >= 0; checkLine--) {
            if ((content[checkLine] & fullLine) == fullLine) {
                content[checkLine] = 0;
            } else {
                if (writeLine != checkLine) {
                    content[writeLine] = content[checkLine];
                    content[checkLine] = 0;
                }
                writeLine--;
            }
        }
    }

    @Override
    public void reset() {
        for (int i = 0; i < height; i++) {
            this.content[i] = 0;
        }
    }

}
