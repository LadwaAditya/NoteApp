package com.ladwa.aditya.notehomelane.ui.base;

/**
 * Created by Aditya on 09-Feb-17.
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
