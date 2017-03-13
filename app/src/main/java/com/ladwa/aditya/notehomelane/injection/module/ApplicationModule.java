package com.ladwa.aditya.notehomelane.injection.module;

import android.app.Application;
import android.content.Context;

import com.ladwa.aditya.notehomelane.injection.scope.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aditya on 09-Feb-17.
 */

@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }


}
