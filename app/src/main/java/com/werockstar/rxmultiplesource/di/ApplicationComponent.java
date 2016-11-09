package com.werockstar.rxmultiplesource.di;

import com.werockstar.rxmultiplesource.view.MainActivity;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
