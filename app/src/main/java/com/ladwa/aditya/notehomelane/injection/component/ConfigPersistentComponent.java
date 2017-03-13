package com.ladwa.aditya.notehomelane.injection.component;


import com.ladwa.aditya.notehomelane.injection.module.ActivityModule;
import com.ladwa.aditya.notehomelane.injection.scope.ConfigPersistent;

import dagger.Component;

/**
 * Created by Aditya on 09-Feb-17.
 */

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);


}
