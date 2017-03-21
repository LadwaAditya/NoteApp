package com.ladwa.aditya.notehomelane.data;

import com.ladwa.aditya.notehomelane.data.local.DbManager;
import com.ladwa.aditya.notehomelane.data.model.Note;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;

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
    public long createOrUpdateNote(Note note) {
        Realm dataRealm = mDbManager.getDataRealm();
        dataRealm.beginTransaction();
        dataRealm.insertOrUpdate(note);
        dataRealm.commitTransaction();
        return note.getId();
    }

    @Override
    public List<Note> getNotes() {
        Realm dataRealm = mDbManager.getDataRealm();
        return dataRealm.where(Note.class).findAll();
    }

    @Override
    public Note getNoteByPrimaryKey(long id) {
        return mDbManager.getDataRealm().where(Note.class).equalTo("id", id).findFirst();
    }

    @Override
    public long deleteNote(long id) {
        Realm dataRealm = mDbManager.getDataRealm();
        Note note = dataRealm.where(Note.class).equalTo("id", id).findFirst();
        long noteId = note.getId();
        dataRealm.beginTransaction();
        note.deleteFromRealm();
        dataRealm.commitTransaction();
        return noteId;
    }
}
