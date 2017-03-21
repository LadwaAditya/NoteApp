package com.ladwa.aditya.notehomelane.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.databinding.ActivityDetailBinding;
import com.ladwa.aditya.notehomelane.ui.add.AddNoteActivity;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

import javax.inject.Inject;

import timber.log.Timber;

public class NoteDetailActivity extends BaseActivity implements NoteDetailContract.View {

    private static final String EXTRA_NOTE_ID = "extra_note_id";
    @Inject NoteDetailPresenter presenter;
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
        setSupportActionBar(mBinding.toolbar);
        long longExtra = getIntent().getLongExtra(EXTRA_NOTE_ID, -1);
        presenter.getNoteByPrimaryKey(longExtra);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                presenter.deleteNote(mNote);
                Timber.d("Called delete");
                return true;
            case R.id.action_edit:
                startActivity(AddNoteActivity.getStartIntent(this, mNote.getId()));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
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
        Glide.with(this).load(mNote.getUrl())
                .into(mBinding.imgAttachment);
    }

    @Override
    public void noteDeleted(long id) {
        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
