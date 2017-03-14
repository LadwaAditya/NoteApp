package com.ladwa.aditya.notehomelane.ui.add;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Aditya on 13-Mar-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddNotePresenterTest {

    @Mock DataManager mockDataManager;
    @Mock AddNoteContract.View mockView;

    private AddNotePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new AddNotePresenter(mockDataManager);
        presenter.attachView(mockView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

    @Test
    public void createNote_shouldReturnResult() throws Exception {
        Note note = TestDataFactory.makeNote("String");
        when(mockDataManager.createOrUpdateNote(note)).thenReturn(note.getId());

        presenter.createNote(note);

        verify(mockView).setUpView();
        verify(mockView).noteSaved();
    }

    @Test
    public void getNoteByPrimaryKey_shouldReturnResult() throws Exception {
        Note note = TestDataFactory.makeNote("String");

        when(mockDataManager.getNoteByPrimaryKey(note.getId())).thenReturn(note);

        presenter.getNoteByPrimaryKey(note.getId());

        verify(mockView).setUpView();
        verify(mockView).setNote(any(Note.class));
    }
}