package com.yihui.life.ui;

import com.yihui.life.Constants;
import com.yihui.life.util.ContentUtil;
import com.yihui.life.controlpanel.UIContent;
import lombok.NonNull;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import static com.yihui.life.AppMain.logger;

/**
 *
 */
public class LifePanel extends JPanel {
    @NonNull private final UIContent uiContent;

    private boolean firstRun = true;

    private static final int BLOCK_SIZE = 15;

    /**
     *
     * @param u ui
     */
    public LifePanel(@NonNull final UIContent u) {
        this.uiContent = u;
        setPreferredSize(new Dimension(BLOCK_SIZE * Constants.PANEL_WIDTH, BLOCK_SIZE * Constants.PANEL_HEIGHT));
    }

    /**
     *
     */
    public void reset() {
        firstRun = true;
    }

    private static final int DRAW_COLOR = 0x081D65;

    /**
     *
     */
    @Override
    public void paint(final Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(DRAW_COLOR));

        logger.warn("LifePanel paint start, " + System.currentTimeMillis()
                + ", firstRun: " + firstRun + ", running: " + uiContent.isRunning());


        paintPanel(g2D);
        logger.warn("LifePanel paint end, " + System.currentTimeMillis());
    }

    private void paintPanel(final Graphics2D g2D) {
        long[] content = uiContent.getPanel().getContent();

        for (int y = 0; y < Constants.PANEL_HEIGHT; y++) {
            for (int x = 0; x < Constants.PANEL_WIDTH; x++) {
                drawRect(x, y, g2D, ContentUtil.getBitAtPosition(content, x, y) != 0);
            }
            logger.warn("Reset panel, line " + y + ", data: " + content[y]);

        }
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
