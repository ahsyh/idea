package com.yihui.tetris.controlpanel;

import lombok.NonNull;

/**
 *
 */
public interface Controller {
    /**
     *
     * @param speed speed value
     */
    void setSpeed(@NonNull Speed speed);

    /**
     *
     * @param uiEngine ui
     */
    void setUiEngine(@NonNull UIEngine uiEngine);

    // Event

    /**
     *
     */
    void init();
    /**
     *
     */
    void start();
    /**
     *
     */
    void stop();
    /**
     *
     */
    void reset();
    /**
     *
     */
    void onTimer();
}
