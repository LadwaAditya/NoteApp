package com.ladwa.aditya.notehomelane.data;

import com.ladwa.aditya.notehomelane.data.model.Note;

import java.util.List;

/**
 * Created by Aditya on 13-Mar-17.
 */

public interface DataRepository {
    long createOrUpdateNote(Note note);

    List<Note> getNotes();

    Note getNoteByPrimaryKey(long id);

    long deleteNote(long id);
}
