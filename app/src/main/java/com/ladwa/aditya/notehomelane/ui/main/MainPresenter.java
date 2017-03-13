package com.ladwa.aditya.notehomelane.ui.main;

import com.ladwa.aditya.notehomelane.data.DataManager;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.injection.scope.ConfigPersistent;
import com.ladwa.aditya.notehomelane.ui.base.BasePresenter;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * Created by Aditya on 13-Mar-17.
 */
@ConfigPersistent
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {


    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View mvpView) {
        super.attachView(mvpView);
        getMvpView().setUpView();
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void createNote(Note note) {
        dataManager.createDummyNote(note);
    }

    @Override
    public void getAllNotes() {
        RealmResults<Note> notes = dataManager.getNotes();
        if (notes.size() > 0) {
            getMvpView().setProjects(notes);
        } else {
            getMvpView().showEmpty();
        }
    }

}
