package com.ladwa.aditya.notehomelane.ui.add;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devdoo.rxpermissions.RxPermission;
import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.databinding.ActivityAddNoteBinding;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import timber.log.Timber;

public class AddNoteActivity extends BaseActivity implements AddNoteContract.View {

    private static final String EXTRA_NOTE_ID = "extra_note_id";
    private final int REQUEST_CODE_CAPTURE_IMAGE = 100;
    @Inject AddNotePresenter presenter;
    private ActivityAddNoteBinding mBinding;
    private String imagePath;
    private Note mNote;
    private boolean imgFlag = false;


    public static Intent getStartIntent(@NonNull Context context) {
        return new Intent(context, AddNoteActivity.class);
    }


    public static Intent getStartIntent(@NonNull Context context, long noteId) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);
        presenter.attachView(this);
        long noteId = getIntent().getLongExtra(EXTRA_NOTE_ID, -1);
        if (noteId != -1) {
            presenter.getNoteByPrimaryKey(noteId);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void setUpView() {
        mBinding.toolbar.setTitle(R.string.title_add_new_note);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                validateFields();
                break;
            case R.id.btn_attach_image:
                startCameraIntent();
                break;
        }
    }

    private void validateFields() {
        if (mBinding.txtNoteTitle.getText().length() >= 5 && mBinding.txtNoteText.getText().length() >= 15) {
            saveNote();
        } else if (mBinding.txtNoteTitle.getText().length() <= 5) {
            mBinding.txtNoteTitle.setError("Title must be grater than 5 characters :)");
        } else if (mBinding.txtNoteText.getText().length() <= 15) {
            mBinding.txtNoteText.setError("Note text must be grater than 15 characters :)");
        }
    }


    private void saveNote() {
        Note newNote = new Note();
        newNote.setTitle(mBinding.txtNoteTitle.getText().toString());
        newNote.setText(mBinding.txtNoteText.getText().toString());
        newNote.setId(mNote == null ? new Date().getTime() : mNote.getId());
        String dateTime = getCurrentDateTime();
        newNote.setCreatedAt(dateTime);
        newNote.setUrl(imgFlag ? imagePath : "");
        presenter.createNote(newNote);
        finish();
    }

    private String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm: aa", Locale.ENGLISH);
        return dateFormat.format(c.getTime());
    }

    private void startCameraIntent() {
        RxPermission.with(this.getFragmentManager()).request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, REQUEST_CODE_CAPTURE_IMAGE);
                    } else {
                        Snackbar.make(mBinding.coordinatorlayout, R.string.permission_error, Snackbar.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAPTURE_IMAGE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri imageUri = getImageUri(this, photo);
            File imageFile = new File(getRealPathFromURI(imageUri));
            imagePath = imageFile.getAbsolutePath();
            Timber.d(imagePath);
            mBinding.imgAttachment.postDelayed(() ->
                    Glide.with(AddNoteActivity.this)
                            .load(imagePath)
                            .into(mBinding.imgAttachment), 1000);
            imgFlag = true;
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void noteSaved() {
        Toast.makeText(this, "Noted Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNote(Note note) {
        mNote = note;
        mBinding.txtNoteTitle.setText(note.getTitle());
        mBinding.txtNoteText.setText(note.getText());
        if (mNote.getUrl().length() > 0) {
            imgFlag = true;
            imagePath = note.getUrl();
        }
        Glide.with(this).load(mNote.getUrl()).into(mBinding.imgAttachment);
    }
}
