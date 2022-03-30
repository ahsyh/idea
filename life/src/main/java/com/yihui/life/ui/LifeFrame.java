package com.yihui.life.ui;

import com.yihui.life.controlpanel.Controller;
import com.yihui.life.controlpanel.KeyHandler;
import com.yihui.life.controlpanel.UIContent;
import com.yihui.life.controlpanel.UIEngine;
import lombok.NonNull;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.yihui.life.AppMain.logger;

/**
 *
 */
public class LifeFrame extends JFrame implements UIEngine, KeyListener {
    @NonNull private final Controller controller;
    @NonNull private final KeyHandler keyHandler;
    @NonNull private final UIContent uiContent;

    private final LifePanel panel;

    /**
     *
     * @param c controller
     * @param k keyHandler
     * @param u uiContent
     */
    public LifeFrame(
            @NonNull final Controller c,
            @NonNull final KeyHandler k,
            @NonNull final UIContent u) {
        super("Life");
        this.panel = new LifePanel(u);
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
            case KEY_R:
                keyHandler.onReset();
                break;
            case KEY_A:
                keyHandler.onSpeedUp();
                break;
            case KEY_S:
                keyHandler.onSlowDown();
                break;
            case KEY_P:
                keyHandler.onPause();
                break;
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
