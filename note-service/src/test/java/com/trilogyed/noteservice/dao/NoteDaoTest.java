package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    NoteDao nDao;

    @Before
    public void setUp(){
        List<Note> nList = nDao.getAllNotes();

        nList.stream()
                .forEach(note -> nDao.deleteNote(note.getNoteId()));
    }

    @After
    public void tearDown(){
        List<Note> nList = nDao.getAllNotes();

        nList.stream()
                .forEach(note -> nDao.deleteNote(note.getNoteId()));
    }

    @Test
    public void addGetDeleteNote(){

        Note note = new Note();
        note.setBookId(7);
        note.setNote("Winner of the 2019 Nebula Award!");

        note = nDao.addNote(note);

        Note note1 = nDao.getNote(note.getNoteId());

        assertEquals(note, note1);

        nDao.deleteNote(note.getNoteId());

        note1 = nDao.getNote(note.getNoteId());

        assertNull(note1);

    }

    @Test
    public void getAllNotes(){
        List<Note> nList = new ArrayList<>();

        List<Note> nList1 = nDao.getAllNotes();

        assertEquals(nList.size(), nList1.size());

        Note note = new Note();
        note.setBookId(7);
        note.setNote("Winner of the 2019 Nebula Award!");
        note = nDao.addNote(note);
        nList.add(note);

        note = new Note();
        note.setBookId(3);
        note.setNote("This book is a must read!");
        note = nDao.addNote(note);
        nList.add(note);

        note = new Note();
        note.setBookId(2);
        note.setNote("Highly recommended for fans of the last book!");
        note = nDao.addNote(note);
        nList.add(note);

        nList1 = nDao.getAllNotes();

        assertEquals(nList.size(), nList1.size());

        assertEquals(nList, nList1);

    }

    @Test
    public void updateNote(){
        Note note = new Note();
        note.setBookId(7);
        note.setNote("Winner of the 2019 Nebula Award!");
        note = nDao.addNote(note);

        note.setNote("Winner of the 2018 Nebula Award");

        nDao.updateNote(note);

        Note note1 = nDao.getNote(note.getNoteId());

        assertEquals(note1, note);

    }

    @Test
    public void getNotesByBook(){
        
    }
}
