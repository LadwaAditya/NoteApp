package com.ladwa.aditya.notehomelane.injection.component;


import com.ladwa.aditya.notehomelane.injection.module.ActivityModule;
import com.ladwa.aditya.notehomelane.injection.scope.PerActivity;
import com.ladwa.aditya.notehomelane.ui.add.AddNoteActivity;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;
import com.ladwa.aditya.notehomelane.ui.detail.NoteDetailActivity;
import com.ladwa.aditya.notehomelane.ui.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Aditya on 09-Feb-17.
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(AddNoteActivity addNoteActivity);

    void inject(NoteDetailActivity noteDetailActivity);

}
