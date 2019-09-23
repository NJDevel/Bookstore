package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import com.trilogyed.bookservice.util.messages.NoteEntry;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.trilogyed.bookservice.service.ServiceLayer.EXCHANGE;
import static com.trilogyed.bookservice.service.ServiceLayer.ROUTING_KEY;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {

    ServiceLayer service;
    BookDao bookDao;
    NoteServiceClient noteClient;
    RabbitTemplate rabbit;

    @Before
    public void setUp() throws Exception{
        setUpBookDaoMock();
        setUpNoteClientMock();
        setUpRabbitMock();

        service = new ServiceLayer(bookDao, noteClient, rabbit);
    }

    private void setUpBookDaoMock(){
        bookDao = mock(BookDaoJdbcTemplateImpl.class);

        Book book0get = new Book();
        book0get.setBookId(732);
        book0get.setTitle("Siddhartha");
        book0get.setAuthor("Herman Hesse");

        Book book0add = new Book();
        book0add.setTitle("Siddhartha");
        book0add.setAuthor("Herman Hesse");

        doReturn(book0get).when(bookDao).addBook(book0add);
        doReturn(book0get).when(bookDao).getBook(732);

        Book bookWithNote = new Book();
        bookWithNote.setBookId(908);
        bookWithNote.setTitle("Siddhartha");
        bookWithNote.setAuthor("Herman Hesse");

        doReturn(bookWithNote).when(bookDao).getBook(908);

        doNothing().when(bookDao).deleteBook(291);
        doReturn(null).when(bookDao).getBook(291);

    }

    private void setUpNoteClientMock(){
        noteClient = mock(NoteServiceClient.class);

        Note note0get = new Note();
        note0get.setNoteId(732);
        note0get.setBookId(732);
        note0get.setNote("A book about a man who becomes enlightened");

        doReturn(note0get).when(noteClient).getNote(732);

        //returns null notes list for newly added book
        List<Note> emptyNotes = new ArrayList<>();
        doReturn(emptyNotes).when(noteClient).getNotesByBook(732);

        Note noteToAdd = new Note();
        noteToAdd.setNoteId(908);
        noteToAdd.setBookId(908);
        noteToAdd.setNote("A book about a man who becomes enlightened");

        List<Note> bookNotes = new ArrayList<>();
        bookNotes.add(noteToAdd);
        doReturn(bookNotes).when(noteClient).getNotesByBook(908);
    }

    private void setUpRabbitMock(){
        rabbit = mock(RabbitTemplate.class);
        Note note = new Note();
        note.setBookId(732);
        note.setNote("A book about a man who becomes enlightened");

        NoteEntry msg = new NoteEntry(note.getBookId(), note.getNote());
        msg.setBookId(note.getBookId());
        msg.setNote(note.getNote());

        doNothing().when(rabbit).convertAndSend(EXCHANGE, ROUTING_KEY, msg);

    }

    @Test
    public void addFindDeleteBook() {
        //add book
        BookViewModel bvm = new BookViewModel();
        bvm.setTitle("Siddhartha");
        bvm.setAuthor("Herman Hesse");

        bvm = service.addBook(bvm);
        //find book
        BookViewModel bvm1 = service.findBook(bvm.getBookId());

        assertEquals(bvm, bvm1);

        //find book with note
        bvm = new BookViewModel();
        bvm.setBookId(908);
        bvm.setTitle("Siddhartha");
        bvm.setAuthor("Herman Hesse");

        Note note = new Note();
        note.setNoteId(908);
        note.setBookId(908);
        note.setNote("A book about a man who becomes enlightened");

        List<Note> bookNote = new ArrayList<>();
        bookNote.add(note);

        bvm.setNotes(bookNote);

        bvm1 = service.findBook(bvm.getBookId());

        assertEquals(bvm, bvm1);

        bvm = new BookViewModel();
        bvm.setBookId(291);

        service.removeBook(bvm.getBookId());

        bvm = service.findBook(bvm.getBookId());

        assertNull(bvm);

    }

    @Test
    public void findAllBooks() {


    }

    @Test
    public void updateBook() {
    }

    @Test
    public void saveNote() {
    }

    @Test
    public void findAllNotes() {
    }

    @Test
    public void updateNote() {
    }

    @Test
    public void findNotesByBook() {
    }


}