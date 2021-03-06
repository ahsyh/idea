package com.yihui.tetris.dagger;

import com.yihui.tetris.Constants;
import com.yihui.tetris.controlpanel.Controller;
import com.yihui.tetris.controlpanel.KeyHandler;
import com.yihui.tetris.controlpanel.Panel;
import com.yihui.tetris.controlpanel.UIContent;
import com.yihui.tetris.impl.ControllerImpl;
import com.yihui.tetris.impl.PanelImpl;
import dagger.Module;
import dagger.Provides;
import lombok.NonNull;

import javax.inject.Singleton;

/**
 *
 */
@Module
public final class GeneralModule {
    @Provides
    @Singleton
    Panel providePanel() {
        Panel p = new PanelImpl();
        p.init(Constants.PANEL_WIDTH, Constants.PANEL_HEIGHT);
        return p;
    }

    @Provides
    @Singleton
    UIContent provideUIContent(@NonNull final Panel panel) {
        final UIContent u = new UIContent();
        u.setPanel(panel);
        return u;
    }

    @Provides
    @Singleton
    Controller provideController(
            @NonNull final UIContent uiContent) {
        return new ControllerImpl(uiContent);
    }

    @Provides
    @Singleton
    KeyHandler provideKeyHandler(@NonNull final Controller controller) {
        return (ControllerImpl) controller;
    }

//    @Provides
//    @Singleton
//    ThreadPoolExecutor provideExecutor() {
//        return new ThreadPoolExecutor(3, 5,
//                60, TimeUnit.SECONDS,
//                new SynchronousQueue<>(true));
//    }
}
