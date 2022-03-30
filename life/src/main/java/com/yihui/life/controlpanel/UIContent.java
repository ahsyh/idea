package com.yihui.life.controlpanel;

import lombok.Data;

/**
 *
 */
@Data
public final class UIContent {
    private Panel panel;
    private boolean running;

    /**
     *
     */
    public void init() {
        getPanel().reset();
        nextStep();
    }

    /**
     *
     */
    public void nextStep() {
    }
}
