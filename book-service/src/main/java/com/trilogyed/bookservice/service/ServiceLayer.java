package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
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
    public ServiceLayer(BookDao bookDao){
        this.bookDao = bookDao;
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
        BookViewModel bvm = new BookViewModel();
        //find note
        return bvm;
    }

    public List<Note> findAllNotes(){
        List<Note> noteList = new ArrayList<>();
        //find book
        return noteList;
    }

    public void updateNote(Note note){
        //update note
    }

    public void removeNote(int noteId){
        //delete note
    }

    public BookViewModel findNotesByBook(int bookId){
        //build bookview model
        BookViewModel bvm = new BookViewModel();
        return bvm;
    }


}
