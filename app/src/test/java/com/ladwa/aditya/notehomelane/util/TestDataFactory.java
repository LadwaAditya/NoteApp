package com.ladwa.aditya.notehomelane.util;

import com.ladwa.aditya.notehomelane.data.model.Note;

import java.util.ArrayList;
import java.util.List;

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


    public static List<Note> makeNotes(int num) {
        ArrayList<Note> noteArrayList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            noteArrayList.add(makeNote("Test" + i));
        }
        return noteArrayList;
    }
}
