package com.yihui.tetris.ui;

import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.controlpanel.UIEngine;
import lombok.NonNull;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.yihui.tetris.AppMain.logger;

/**
 *
 */
public class TetrisFrame extends JFrame implements UIEngine, KeyListener {
    @NonNull private final Controller controller;
    @NonNull private final KeyHandler keyHandler;
    @NonNull private final UIContent uiContent;

    private final TetrisPanel panel;

    /**
     *
     * @param c controller
     * @param k keyHandler
     * @param u uiContent
     */
    public TetrisFrame(
            @NonNull final Controller c,
            @NonNull final KeyHandler k,
            @NonNull final UIContent u) {
        super("Tetris");
        this.panel = new TetrisPanel(u);
        this.controller = c;
        this.keyHandler = k;
        this.uiContent = u;
    }

    /**
     *
     */
    public void init() {
        controller.setUiEngine(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();

        setLayout(null);
        addKeyListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     *
     */
    @Override
    public void display() {
        panel.repaint();
    }

    /**
     *
     */
    @Override
    public void reset() {
        panel.reset();
        panel.repaint();
    }

    /**
     *
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    private static final int KEY_SPACE = 32;
    private static final int KEY_ARROW_LEFT = 37;
    private static final int KEY_ARROW_RIGHT = 39;
    private static final int KEY_ARROW_UP = 38;
    private static final int KEY_ARROW_DOWN = 40;
    private static final int KEY_R = 82;
    private static final int KEY_A = 65;
    private static final int KEY_S = 83;
    private static final int KEY_P = 80;

    /**
     *
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        int code = e.getKeyCode();
        logger.warn(" you pressed key code: " + code);
        switch (code) {
            case KEY_SPACE:
                keyHandler.onBottom();
                break;
            case KEY_ARROW_LEFT:
                keyHandler.onLeft();
                break;
            case KEY_ARROW_RIGHT:
                keyHandler.onRight();
                break;
            case KEY_ARROW_UP:
                keyHandler.onRotateCCW();
                break;
            case KEY_ARROW_DOWN:
                keyHandler.onRotateCW();
                break;
            case KEY_R:
                keyHandler.onReset();
                break;
            case KEY_A:
                keyHandler.onSpeedUp();
                break;
            case KEY_S:
                keyHandler.onSlowDown();
            case KEY_P:
                keyHandler.onPause();
            default:
                break;
        }
    }

    /**
     *
     */
    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
