package com.yihui.tetris.ui;

import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.util.ContentUtil;
import lombok.NonNull;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;

import static com.yihui.tetris.AppMain.logger;
import static com.yihui.tetris.Constants.PANEL_HEIGHT;
import static com.yihui.tetris.Constants.PANEL_WIDTH;

public class TetrisPanel extends JPanel {
    @NonNull private final UIContent uiContent;
    @NonNull private final long[] currContent;

    private boolean firstRun = true;

    private final static int BLOCK_SIZE = 40;

    public TetrisPanel(@NonNull final UIContent uiContent) {
        this.uiContent = uiContent;
        this.currContent = new long[PANEL_HEIGHT];
        setPreferredSize(new Dimension(BLOCK_SIZE * PANEL_WIDTH, BLOCK_SIZE * PANEL_HEIGHT));
    }

    public void reset() {
        firstRun = true;
        ContentUtil.clearContent(currContent);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(0x081D65));

        logger.warn("TetrisPanel paint start, " + System.currentTimeMillis()
                + ", firstRun: " + firstRun + ", running: " + uiContent.isRunning());
        if (firstRun || !uiContent.isRunning()) {
            firstRun = false;
            ContentUtil.clearContent(currContent);
        } else {
            prepCurrentContent(currContent);
        }

        paintPanel(g2D);
        logger.warn("TetrisPanel paint end, " + System.currentTimeMillis());
    }

    private void paintPanel(Graphics2D g2D) {
        for (int x = 0; x < PANEL_WIDTH; x++) {
            for (int y = 0; y < PANEL_HEIGHT; y++) {
                drawRect(x, y, g2D, ContentUtil.getBitAtPosition(currContent, x, y) != 0);
            }
        }
    }

    private void prepCurrentContent(long[] content) {
        synchronized (uiContent) {
            ContentUtil.copyContent(uiContent.getPanel().getContent(), content);
            ContentUtil.mergeBrick(content, uiContent.getCurrent(),
                    uiContent.getBrickPositionX(), uiContent.getBrickPositionY());
            logger.warn("TetrisPanel paint, curr index: " + uiContent.getCurrent().getIndex()
                    + ", rotation: " + uiContent.getCurrent().getRotation());
        }

    }

    private void drawRect(int x, int y, Graphics2D g2D, boolean fill) {
        if (fill) {
            g2D.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        } else {
            g2D.clearRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            g2D.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }
    }
}
