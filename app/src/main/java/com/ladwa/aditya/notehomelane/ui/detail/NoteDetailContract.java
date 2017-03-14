package com.ladwa.aditya.notehomelane.ui.detail;

import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.ui.base.MvpPresenter;
import com.ladwa.aditya.notehomelane.ui.base.MvpView;

/**
 * Created by Aditya on 14-Mar-17.
 */

public class NoteDetailContract {
    interface View extends MvpView {
        void setNote(Note note);

        void noteDeleted(long id);
    }

    interface Presenter extends MvpPresenter<NoteDetailContract.View> {
        void getNoteByPrimaryKey(long id);

        void deleteNote(Note note);
    }
}
