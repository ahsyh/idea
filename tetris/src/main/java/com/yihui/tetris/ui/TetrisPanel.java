package com.yihui.tetris.ui;

import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.util.ContentUtil;
import lombok.NonNull;

import javax.swing.*;

import java.awt.*;

import static com.yihui.tetris.Constants.PANEL_HEIGHT;
import static com.yihui.tetris.Constants.PANEL_WIDTH;

public class TetrisPanel extends JPanel {
    @NonNull private final UIContent uiContent;
    @NonNull private final long[] saveContent;
    @NonNull private final long[] currContent;

    private final long[] defaultContent = new long[1];

    private boolean isContentInit;

    private final static int BLOCK_SIZE = 50;

    public TetrisPanel(@NonNull final UIContent uiContent) {
        this.uiContent = uiContent;
        this.saveContent = new long[PANEL_HEIGHT];
        this.currContent = new long[PANEL_HEIGHT];
        this.isContentInit = false;
        setPreferredSize(new Dimension(BLOCK_SIZE * PANEL_WIDTH, BLOCK_SIZE * PANEL_HEIGHT));
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("TetrisPanel paint start, " + System.currentTimeMillis());

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(new Color(0x9020F0));
        long[] content = defaultContent;
        boolean needPaint = false;

        synchronized (uiContent) {
            if (uiContent.getCurrent() != null) {
                needPaint = true;
                content = uiContent.getPanel().getContent();
                ContentUtil.copyContent(content, currContent);
                ContentUtil.mergeBrick(currContent, uiContent.getCurrent(),
                        uiContent.getBrickPositionX(), uiContent.getBrickPositionY());
                System.out.println("TetrisPanel paint, curr index: " + uiContent.getCurrent().getIndex()
                        + ", rotation: " + uiContent.getCurrent().getRotation());
            }
        }

        if (!needPaint) return;

        for (int x = 0; x < PANEL_WIDTH; x++) {
            for (int y = 0; y < PANEL_HEIGHT; y++) {
//                drawRect(x, y, g2D, ContentUtil.getBitAtPosition(currContent, x, y) != 0);
                if (ContentUtil.getBitAtPosition(saveContent, x, y) != ContentUtil.getBitAtPosition(currContent, x, y)) {
                    drawRect(x, y, g2D, ContentUtil.getBitAtPosition(currContent, x, y) != 0);
                }
            }
        }

        ContentUtil.copyContent(currContent, saveContent);
        System.out.println("TetrisPanel paint end, " + System.currentTimeMillis());

    }

    private void drawRect(int x, int y, Graphics2D g2D, boolean fill) {
        if (fill) {
            g2D.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        } else {
            g2D.clearRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        }
    }
}
