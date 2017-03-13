package com.ladwa.aditya.notehomelane.ui.add;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.databinding.ActivityAddNoteBinding;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

import javax.inject.Inject;

import timber.log.Timber;

public class AddNoteActivity extends BaseActivity implements AddNoteContract.View {

    @Inject AddNotePresenter presenter;
    private ActivityAddNoteBinding mBinding;

    public static Intent getStartIntent(@NonNull Context context) {
        return new Intent(context, AddNoteActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void setUpView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);
        setSupportActionBar(mBinding.toolbar);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                Timber.d("Saving note");
                break;
            case R.id.img_note_attachment:
                Timber.d("Clicked Image");
                break;
        }
    }
}
