package com.ladwa.aditya.notehomelane.data;

import com.ladwa.aditya.notehomelane.data.model.Note;

import io.realm.RealmResults;

/**
 * Created by Aditya on 13-Mar-17.
 */

public interface DataRepository {
    long createDummyNote(Note note);

    RealmResults<Note> getNotes();
}
