package com.trilogyed.bookservice.service;

import com.netflix.discovery.converters.Auto;
import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private BookDao bookDao;

    @Autowired
    private NoteServiceClient noteClient;


    @Autowired
    public ServiceLayer(BookDao bookDao, NoteServiceClient noteClient){
        this.bookDao = bookDao;
        this.noteClient = noteClient;
    }

    /**************************************************************
     Book API
     **************************************************************/
    @Transactional
    public BookViewModel addBook(BookViewModel bvm){
        //Save book
        return bvm;
    }

    public BookViewModel findBook(int id) {
        BookViewModel bvm = new BookViewModel();
        //Retrieve book
        return bvm;
    }

    public List<BookViewModel> findAllBooks() {
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        //Retrieve books for bvmList
        return bookViewModelList;
    }

    public void updateBook(BookViewModel bvm){
        //Update book
    }

    public void removeBook(int id){
        //delete book

    }

    private BookViewModel buildBookViewModel(Book book){
        BookViewModel bvm = new BookViewModel();
        //get book and build model

        return bvm;
    }


    /**************************************************************
     Note API
     **************************************************************/

    public Note saveNote(Note note) {
        //Client service
        return note;
    }

    public BookViewModel findNote(int noteId){

        Note note = noteClient.getNote(noteId);

        Book book = bookDao.getBook(note.getBookId());

        List<Note> noteList = new ArrayList<>();

        noteList.add(note);

        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        bvm.setNotes(noteList);

        return bvm;

    }

    public List<Note> findAllNotes(){
        //find book
        return noteClient.getAllNotes();
    }

    public void updateNote(Note note){
        //update note
    }

    public void removeNote(int noteId){
        //delete note
    }

    public BookViewModel findNotesByBook(int bookId){

        Book book = bookDao.getBook(bookId);

        List<Note> noteList = noteClient.getNotesByBook(bookId);

        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        bvm.setNotes(noteList);

        return bvm;
    }


}
