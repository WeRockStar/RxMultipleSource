package com.werockstar.rxmultiplesource.di;

import com.werockstar.rxmultiplesource.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = HttpModule.class)
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
