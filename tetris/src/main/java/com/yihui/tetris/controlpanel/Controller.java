package com.yihui.tetris.controlpanel;

import lombok.NonNull;

public interface Controller {
    void setSpeed(@NonNull final Speed speed);
    Speed getSpeed();
    void setUiEngine(@NonNull final UIEngine uiEngine);

    // Event
    void onTimer();
    void onInit();
    void start();
    void stop();
    void reset();

}
