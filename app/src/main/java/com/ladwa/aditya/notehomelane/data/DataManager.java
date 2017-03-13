package com.ladwa.aditya.notehomelane.data;

import com.ladwa.aditya.notehomelane.data.local.DbManager;
import com.ladwa.aditya.notehomelane.data.model.Note;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Aditya on 13-Mar-17.
 */
@Singleton
public class DataManager implements DataRepository {
    private final DbManager mDbManager;

    @Inject
    public DataManager(DbManager mDbManager) {
        this.mDbManager = mDbManager;
    }

    @Override
    public void createDummyNote(Note note) {
        note.setId(getNotes().size() + 1);
        Realm dataRealm = mDbManager.getDataRealm();
        dataRealm.beginTransaction();
        dataRealm.insert(note);
        dataRealm.commitTransaction();
    }

    @Override
    public RealmResults<Note> getNotes() {
        Realm dataRealm = mDbManager.getDataRealm();
        return dataRealm.where(Note.class).findAll();
    }
}
