package com.yihui.tetris.ui;

import com.yihui.tetris.controlpanel.Brick;
import com.yihui.tetris.controlpanel.UIContent;
import lombok.NonNull;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

import static com.yihui.tetris.AppMain.logger;
import static com.yihui.tetris.ui.TetrisFrame.BLOCK_SIZE;

/**
 *
 */
public class NextBrickPanel  extends JPanel {
    @NonNull private final UIContent uiContent;

    private static final int MAX_BRICK_SIZE = 4;

    /**
     *
     * @param u ui content
     */
    public NextBrickPanel(@NonNull final UIContent u) {
        this.uiContent = u;
        setPreferredSize(new Dimension(
                BLOCK_SIZE * MAX_BRICK_SIZE,
                BLOCK_SIZE * (1 + MAX_BRICK_SIZE)));
    }

    private static final int DRAW_COLOR = 0x081D65;
    private static final int FONT_SIZE = 16;

    /**
     *
     */
    @Override
    public void paint(final Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(DRAW_COLOR));
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE));

        paintPanel(g2D);
        logger.warn("BrickPanel paint end, " + System.currentTimeMillis());
    }

    private static final int MAX_BRICK_WIDTH = 4;
    private static final int MAX_BRICK_HEIGHT = 4;

    private void paintPanel(final Graphics2D g2D) {
        Brick b = uiContent.getNext();
        for (int x = 0; x < MAX_BRICK_WIDTH; x++) {
            for (int y = 0; y < MAX_BRICK_HEIGHT; y++) {
                drawRect(x, y, g2D, b != null
                        && b.getBitAtPosition(x, y) == 1);
            }
        }
        g2D.drawString("Score : " + uiContent.getScore(), 0, (MAX_BRICK_HEIGHT + 1) * BLOCK_SIZE);
    }

    private void drawRect(final int x, final int y, final Graphics2D g2D, final boolean fill) {
        if (fill) {
            g2D.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        } else {
            g2D.clearRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            g2D.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }
    }
}
