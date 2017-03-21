package com.ladwa.aditya.notehomelane.ui.main;

import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.ui.base.MvpPresenter;
import com.ladwa.aditya.notehomelane.ui.base.MvpView;

import java.util.List;

/**
 * Created by Aditya on 13-Mar-17.
 */

public interface MainContract {
    interface View extends MvpView {
        void setProjects(List<Note> notes);

        void showEmpty();
    }

    interface Presenter extends MvpPresenter<View> {

        void getAllNotes();
    }
}
