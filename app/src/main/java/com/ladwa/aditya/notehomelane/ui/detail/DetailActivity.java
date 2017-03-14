package com.ladwa.aditya.notehomelane.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

public class DetailActivity extends BaseActivity {

    public static Intent getStartIntent(@NonNull Context context) {
        return new Intent(context, DetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
