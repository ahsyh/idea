package com.yihui.tetris.ui;

import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.controlpanel.UIEngine;
import lombok.NonNull;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisFrame extends JFrame implements UIEngine, KeyListener {
    @NonNull private final Controller controller;
    @NonNull private final KeyHandler keyHandler;
    @NonNull private final UIContent uiContent;

    private final TetrisPanel panel;

    public TetrisFrame(
            @NonNull final Controller controller,
            @NonNull final KeyHandler keyHandler,
            @NonNull final UIContent uiContent) {
        super("Tetris");
        this.panel = new TetrisPanel(uiContent);
        this.controller = controller;
        this.keyHandler = keyHandler;
        this.uiContent = uiContent;
    }

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

    @Override
    public void display() {
        panel.repaint();
    }

    @Override
    public void reset() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(" you pressed key code: " + code);
        switch(code) {
            case 37:
                keyHandler.onLeft();
                break;
            case 39:
                keyHandler.onRight();
                break;
            case 38:
                keyHandler.onRotateCCW();
                break;
            case 32:
                keyHandler.onRotateCW();
                break;
            case 40:
                keyHandler.onBottom();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
