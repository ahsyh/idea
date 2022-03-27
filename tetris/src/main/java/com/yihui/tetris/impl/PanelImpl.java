package com.yihui.tetris.impl;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.Panel;
import lombok.Getter;

import static com.yihui.tetris.Constants.PANEL_HEIGHT;
import static com.yihui.tetris.Constants.PANEL_WIDTH;

@Getter
public class PanelImpl implements Panel {
    private long[] content;
    private int width;
    private int height;

    private static long FULL_LINE;
    static {
        FULL_LINE = 0;
        long bit = 0b1;
        for(int i = 0; i < PANEL_WIDTH; i++) {
            FULL_LINE |= bit;
            bit <<= 1;
        }
    }

    @Override
    public void init(int w, int h) {
        this.width = w;
        this.height = h;
        this.content = new long[h];
    }

    @Override
    public void cleanFullLine() {
        int checkLine = PANEL_HEIGHT - 1;
        int writeLine = PANEL_HEIGHT - 1;

        for(; checkLine >= 0; checkLine--) {
            if ((content[checkLine] & FULL_LINE) == FULL_LINE) {
                checkLine--;
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
