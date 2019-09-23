package com.trilogyed.noteservice.service;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private NoteDao noteDao;

    @Autowired
    public ServiceLayer(NoteDao noteDao){
        this.noteDao=noteDao;
    }

    public Note saveNote(Note note) {
        return noteDao.addNote(note);
    }

    public Note findNote(int noteId){
        Note note = noteDao.getNote(noteId);
        return note;
    }

    public List<Note> findAllNotes(){
        return noteDao.getAllNotes();
    }

    public void updateNote(Note note){
        noteDao.updateNote(note);
    }

    public void removeNote(int noteId){
        noteDao.deleteNote(noteId);
    }

    public List<Note> findNotesByBook(int bookId){
        List<Note> bookNotes = new ArrayList<>();
        try {
            bookNotes = noteDao.getNotesByBook(bookId);
        } catch (EmptyResultDataAccessException e) {
            return bookNotes;
        }
        return bookNotes;
    }
}
