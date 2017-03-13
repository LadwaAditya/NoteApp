package com.ladwa.aditya.notehomelane.ui.add;

import com.ladwa.aditya.notehomelane.data.DataManager;
import com.ladwa.aditya.notehomelane.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Aditya on 13-Mar-17.
 */

public class AddNotePresenter extends BasePresenter<AddNoteContract.View> implements AddNoteContract.Presenter {

    private final DataManager dataManager;

    @Inject
    public AddNotePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void attachView(AddNoteContract.View mvpView) {
        super.attachView(mvpView);
        getMvpView().setUpView();
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
