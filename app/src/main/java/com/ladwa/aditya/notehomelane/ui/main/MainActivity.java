package com.ladwa.aditya.notehomelane.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.databinding.ActivityMainBinding;
import com.ladwa.aditya.notehomelane.ui.adapter.NoteAdapter;
import com.ladwa.aditya.notehomelane.ui.add.AddNoteActivity;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject MainPresenter mPresenter;

    private ActivityMainBinding mBinding;
    private NoteAdapter noteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    public void setUpView() {
        setSupportActionBar(mBinding.toolbar);
        mBinding.included.recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.getAllNotes();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void setProjects(List<Note> notes) {
        noteAdapter = new NoteAdapter(notes);
        mBinding.included.recyclerViewNotes.setAdapter(noteAdapter);
        mBinding.included.txtError.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmpty() {
        mBinding.included.txtError.setVisibility(View.VISIBLE);
    }


    public void onClickFab(View view) {
        startActivity(AddNoteActivity.getStartIntent(this));
    }
}
