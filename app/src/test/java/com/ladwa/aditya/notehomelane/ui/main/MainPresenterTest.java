package com.ladwa.aditya.notehomelane.ui.main;

import com.ladwa.aditya.notehomelane.data.DataManager;
import com.ladwa.aditya.notehomelane.data.model.Note;
import com.ladwa.aditya.notehomelane.util.TestDataFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Aditya on 20-Mar-17.
 */
public class MainPresenterTest {

    @Mock DataManager mockDataManager;
    @Mock MainContract.View mockView;

    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(mockDataManager);
        presenter.attachView(mockView);
    }

    @After
    public void tearDown() throws Exception {
        presenter.detachView();
    }

    @Test
    public void getAllNotes_showReturnResult() throws Exception {
        List<Note> notes = TestDataFactory.makeNotes(5);

        when(mockDataManager.getNotes()).thenReturn(notes);
        presenter.getAllNotes();

        verify(mockView).setProjects(notes);
        verify(mockView,never()).showEmpty();

    }

    @Test
    public void getAllNotes_shouldReturnEmptyResult() throws Exception {
        List<Note> notes = TestDataFactory.makeNotes(0);

        when(mockDataManager.getNotes()).thenReturn(notes);
        presenter.getAllNotes();

        verify(mockView,never()).setProjects(notes);
        verify(mockView).showEmpty();
    }
}