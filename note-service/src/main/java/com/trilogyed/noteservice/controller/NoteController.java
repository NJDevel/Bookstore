package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.model.Note;
import com.trilogyed.noteservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class NoteController {

    @Autowired
    ServiceLayer sl;

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody @Valid Note note) {
        return sl.saveNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable("id") int noteId) {
        Note note = sl.findNote(noteId);
        if (note == null)
            throw new IllegalArgumentException("Note could not be retrieved for id " + noteId);
        return note;
    }

    @RequestMapping(value = "/notes/book/{bookId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNoteByBook(@PathVariable("bookId") int bookId) {
        return sl.findNotesByBook(bookId);
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes(){
        return sl.findAllNotes();
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateNote(@RequestBody @Valid Note note) {
        sl.updateNote(note);
        return "Note successfully updated.";
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteNote(@PathVariable("id") int noteId) {
        sl.removeNote(noteId);
        return "Note successfully deleted.";
    }
}
