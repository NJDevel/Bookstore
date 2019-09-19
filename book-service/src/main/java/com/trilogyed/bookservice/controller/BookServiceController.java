package com.trilogyed.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookServiceController {

    @Autowired
    Service sl;


    // -----------------------BOOKS-----------------------------------------------------

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel createBook(@RequestBody @Valid BookViewModel bvm) {
        return sl.addBook(bvm);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBook(@PathVariable("id") int bookId) {
        BookViewModel bvm = sl.findBookById(id);
        if (bvm == null)
            throw new NotFoundException("Book could not be retrieved for id " + bookId);
        return bvm;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks(){
        return sl.getAllBooks();
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateBook(@PathVariable("id") int bookId, @RequestBody @Valid BookViewModel bvm) {
        sl.updateBook(bvm);
        return "Book successfully updated.";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteBook(@PathVariable("id") int bookId) {
        sl.deleteBook(bookId);
        return "Book successfully deleted.";
    }

    // ---------------------NOTES---------------------------------------------------

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody @Valid Note note) {
        return sl.addNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getNote(@PathVariable("id") int noteId) {
        BookViewModel bvm = sl.getNote(noteId);
        if (bvm == null)
            throw new NotFoundException("Note could not be retrieved for id " + noteId);
        return bvm;
    }

    @RequestMapping(value = "/notes/book/{book_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getNote(@PathVariable("id") int bookId) {
        BookViewModel bvm = sl.getNotesByBook(bookId);
        if (bvm == null)
            throw new NotFoundException("Note could not be retrieved for id " + bookId);
        return bvm;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes(){
        return sl.getAllNotes();
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateNote(@PathVariable("id") int noteId, @RequestBody @Valid Note note) {
        sl.updateNote(nvm);
        return "Note successfully updated.";
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteNote(@PathVariable("id") int noteId) {
        sl.deleteNote(noteId);
        return "Note successfully deleted.";
    }

}
