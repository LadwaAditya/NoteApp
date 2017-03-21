package com.ladwa.aditya.notehomelane.injection.component;

import android.app.Application;
import android.content.Context;

import com.ladwa.aditya.notehomelane.data.DataManager;
import com.ladwa.aditya.notehomelane.data.local.DbManager;
import com.ladwa.aditya.notehomelane.injection.module.ApplicationModule;
import com.ladwa.aditya.notehomelane.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aditya on 09-Feb-17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    DbManager getDbManager();
}