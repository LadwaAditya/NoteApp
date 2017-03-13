package com.ladwa.aditya.notehomelane.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ladwa.aditya.notehomelane.R;
import com.ladwa.aditya.notehomelane.databinding.ActivityMainBinding;
import com.ladwa.aditya.notehomelane.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject MainPresenter mPresenter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    public void setUpView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
    }
}
