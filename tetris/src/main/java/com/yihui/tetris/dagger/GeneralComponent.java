package com.yihui.tetris.dagger;

import com.yihui.tetris.AppMain;
import dagger.Component;

import javax.inject.Singleton;

/**
 *
 */
@Singleton
@Component(modules = { GeneralModule.class })
public interface GeneralComponent {
    /**
     *
     * @param main inject class
     */
    void inject(AppMain main);
}
