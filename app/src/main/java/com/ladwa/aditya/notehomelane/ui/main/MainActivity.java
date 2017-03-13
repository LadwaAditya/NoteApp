package com.ladwa.aditya.notehomelane.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.databinding.ActivityMainBinding;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
    }

}
