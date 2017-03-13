package com.ladwa.aditya.notehomelane.util;

import com.ladwa.aditya.notehomelane.data.model.Note;

/**
 * Created by Aditya on 13-Mar-17.
 */

public class TestDataFactory {

    public static Note makeNote(String s) {
        Note note = new Note();
        note.setId(2L);
        note.setTitle("Title " + s);
        note.setText("Text " + s);
        note.setUrl("Url " + s);
        return note;
    }
}
