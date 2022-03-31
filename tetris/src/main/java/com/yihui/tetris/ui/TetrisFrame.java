package com.yihui.tetris.ui;

import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.controlpanel.UIEngine;
import lombok.NonNull;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.yihui.tetris.AppMain.logger;

/**
 *
 */
public class TetrisFrame extends JFrame implements UIEngine, KeyListener {
    /**
     *
     */
    public static final int BLOCK_SIZE = 40;

    @NonNull private final Controller controller;
    @NonNull private final KeyHandler keyHandler;
    @NonNull private final UIContent uiContent;

    private final TetrisPanel gamePanel;
    private final NextBrickPanel brickPanel;
    private final JPanel mainPanel;
    private final JLabel scoreLabel;

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
        this.mainPanel = new JPanel();
        this.gamePanel = new TetrisPanel(u);
        this.brickPanel = new NextBrickPanel(u);
        this.scoreLabel = new JLabel();
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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(gamePanel);
        mainPanel.add(brickPanel);

        add(mainPanel);
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
        gamePanel.repaint();
        brickPanel.repaint();
        scoreLabel.setText("Score: ");
    }

    /**
     *
     */
    @Override
    public void reset() {
        gamePanel.reset();
        gamePanel.repaint();
        brickPanel.repaint();
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
