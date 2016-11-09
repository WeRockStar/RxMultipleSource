package com.werockstar.rxmultiplesource;


import android.app.Application;

import com.werockstar.rxmultiplesource.di.ApplicationComponent;
import com.werockstar.rxmultiplesource.di.DaggerApplicationComponent;
import com.werockstar.rxmultiplesource.di.HttpModule;

public class MainApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .httpModule(new HttpModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
