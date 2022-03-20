package com.yihui.tetris.controlpanel;

import com.sun.istack.internal.NotNull;

public interface Controller {
    void setSpeed(@NotNull final Speed speed);
    void setPanel(@NotNull final Panel panel);
    void setUIEngine(@NotNull final UIEngine uiEngine);
    void start();
    void stop();
    void clear();
}
