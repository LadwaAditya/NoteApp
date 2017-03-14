package com.ladwa.aditya.notehomelane.ui.add;

import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.ui.base.MvpPresenter;
import com.ladwa.aditya.notehomelane.ui.base.MvpView;

/**
 * Created by Aditya on 13-Mar-17.
 */

public class AddNoteContract {
    interface View extends MvpView {
        void noteSaved();

        void setNote(Note note);

    }

    interface Presenter extends MvpPresenter<View> {
        void createNote(Note note);

        void getNoteByPrimaryKey(long id);

    }
}
