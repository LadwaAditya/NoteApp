package com.ladwa.aditya.notehomelane.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.databinding.ActivityDetailBinding;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

import javax.inject.Inject;

public class NoteDetailActivity extends BaseActivity implements NoteDetailContract.View {

    @Inject NoteDetailPresenter presenter;

    private static final String EXTRA_NOTE_ID = "extra_note_id";

    private ActivityDetailBinding mBinding;
    private Note mNote;

    public static Intent getStartIntent(@NonNull Context context, long noteId) {
        Intent intent = new Intent(context, NoteDetailActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void setUpView() {
        long longExtra = getIntent().getLongExtra(EXTRA_NOTE_ID, -1);
        presenter.getNoteByPrimeryKey(longExtra);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void setNote(Note note) {
        mNote = note;
        mBinding.toolbar.setTitle(mNote.getTitle());
        mBinding.txtDetail.setText(mNote.getText());
        Glide.with(this).load(mNote.getUrl()).into(mBinding.imgAttachment);
    }
}
