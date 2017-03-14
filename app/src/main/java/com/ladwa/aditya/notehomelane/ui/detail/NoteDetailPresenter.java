package com.ladwa.aditya.notehomelane.ui.detail;

import com.ladwa.aditya.notehomelane.data.DataManager;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Aditya on 14-Mar-17.
 */

public class NoteDetailPresenter extends BasePresenter<NoteDetailContract.View> implements NoteDetailContract.Presenter {


    private final DataManager dataManager;

    @Inject
    public NoteDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(NoteDetailContract.View mvpView) {
        super.attachView(mvpView);
        getMvpView().setUpView();
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void getNoteByPrimaryKey(long id) {
        Note noteByPrimaryKey = dataManager.getNoteByPrimaryKey(id);
        getMvpView().setNote(noteByPrimaryKey);
    }

    @Override
    public void deleteNote(Note note) {
        long id = dataManager.deleteNote(note.getId());
        getMvpView().noteDeleted(id);
    }
}
