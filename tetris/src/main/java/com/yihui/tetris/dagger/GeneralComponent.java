package com.yihui.tetris.dagger;

import com.yihui.tetris.AppMain;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { GeneralModule.class })
public interface GeneralComponent {
    // allow to inject into our Main class
    // method name not important
    void inject(AppMain main);
}
