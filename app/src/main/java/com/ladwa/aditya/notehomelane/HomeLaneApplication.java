package com.ladwa.aditya.notehomelane;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.ladwa.aditya.notehomelane.injection.component.ApplicationComponent;
import com.ladwa.aditya.notehomelane.injection.component.DaggerApplicationComponent;
import com.ladwa.aditya.notehomelane.injection.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Aditya on 13-Mar-17.
 */

public class HomeLaneApplication extends Application {
    ApplicationComponent mApplicationComponent;

    public static HomeLaneApplication get(Context context) {
        return (HomeLaneApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build()
            );
            LeakCanary.install(this);
        }
    }


    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
