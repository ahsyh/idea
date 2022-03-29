package com.yihui.tetris.controlpanel;

import lombok.NonNull;

public interface Controller {
    void setSpeed(@NonNull final Speed speed);
    void setUiEngine(@NonNull final UIEngine uiEngine);

    // Event
    void onTimer();
    void init();
    void start();
    void stop();
    void reset();

}
