package com.ladwa.aditya.notehomelane.ui.detail;

import com.ladwa.aditya.notehomelane.data.DataManager;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.util.TestDataFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Aditya on 14-Mar-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteDetailPresenterTest {

    @Mock DataManager mockDataManager;
    @Mock NoteDetailContract.View mockView;

    private NoteDetailPresenter presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new NoteDetailPresenter(mockDataManager);
        presenter.attachView(mockView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

    @Test
    public void getNoteByPrimaryKey_shouldReturnResult() throws Exception {
        Note note = TestDataFactory.makeNote("String");

        when(mockDataManager.getNoteByPrimaryKey(note.getId())).thenReturn(note);

        presenter.getNoteByPrimaryKey(note.getId());

        verify(mockView).setUpView();
        verify(mockView).setNote(any(Note.class));
    }

    @Test
    public void deleteNote() throws Exception {
        Note note = TestDataFactory.makeNote("String");
        when(mockDataManager.deleteNote(note.getId())).thenReturn(note.getId());

        presenter.deleteNote(note);

        verify(mockView).setUpView();
        verify(mockView).noteDeleted(anyLong());
    }

}