package com.yihui.tetris.threads;

import com.yihui.tetris.controlpanel.Controller;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

@RequiredArgsConstructor
public class EventListenerManager {
    @NonNull final private Controller controller;
    @NonNull final private ScheduledExecutorService executorService;

    public void start(long interval) {
    }

    public void stop() {
    }
}
