package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.service.ServiceLayer;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class BookServiceController {

    @Autowired
    ServiceLayer sl;






    // -----------------------BOOKS-----------------------------------------------------

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel createBook(@RequestBody @Valid BookViewModel bvm) {
        return sl.addBook(bvm);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBook(@PathVariable("id") int bookId) {
        BookViewModel bvm = sl.findBook(bookId);
        if (bvm == null)
            throw new IllegalArgumentException("Book could not be retrieved for id " + bookId);
        return bvm;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks(){
        return sl.findAllBooks();
    }

    @RequestMapping(value = "/books", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateBook(@RequestBody @Valid BookViewModel bvm) {
        sl.updateBook(bvm);
        return "Book successfully updated.";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteBook(@PathVariable("id") int bookId) {
        sl.removeBook(bookId);
        return "Book successfully deleted.";
    }

    // ---------------------NOTES---------------------------------------------------

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody @Valid Note note) {
        return sl.saveNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getNote(@PathVariable("id") int noteId) {
        BookViewModel bvm = sl.findNote(noteId);
        if (bvm == null)
            throw new IllegalArgumentException("Note could not be retrieved for id " + noteId);
        return bvm;
    }

    @RequestMapping(value = "/notes/book/{bookId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getNoteByBook(@PathVariable("bookId") int bookId) {
        BookViewModel bvm = sl.findNotesByBook(bookId);
        if (bvm == null)
            throw new IllegalArgumentException("Note could not be retrieved for id " + bookId);
        return bvm;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes(){
        return sl.findAllNotes();
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateNote(@PathVariable("id") int noteId, @RequestBody @Valid Note note) {
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
