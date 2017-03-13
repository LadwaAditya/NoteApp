package com.ladwa.aditya.notehomelane.data;

import com.ladwa.aditya.notehomelane.data.local.DbManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Aditya on 13-Mar-17.
 */
@Singleton
public class DataManager {
    private final DbManager mDbManager;

    @Inject
    public DataManager(DbManager mDbManager) {
        this.mDbManager = mDbManager;
    }
}
