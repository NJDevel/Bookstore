package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;

import java.util.List;

public class NoteDaoJdbcTemplateImpl implements NoteDao {


    @Override
    public Note addNote(Note note) {
        return null;
    }

    @Override
    public Note getNote(int noteId) {
        return null;
    }

    @Override
    public List<Note> getAllNotes() {
        return null;
    }

    @Override
    public List<Note> getNotesByBook(int bookId) {
        return null;
    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public void deleteNote(int noteId) {

    }
}
