package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.messages.NoteEntry;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private BookDao bookDao;
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ServiceLayer(BookDao bookDao, RabbitTemplate rabbitTemplate){
        this.bookDao = bookDao;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**************************************************************
     Book API
     **************************************************************/
    @Transactional
    public BookViewModel addBook(BookViewModel bvm){
        //Save book
        Book book = new Book();
        book.setTitle(bvm.getTitle());
        book.setAuthor(bvm.getAuthor());
        book = bookDao.addBook(book);
        bvm = buildBookViewModel(book);
        return bvm;
    }

    public BookViewModel findBook(int id) {
        //Retrieve book
        Book book = bookDao.getBook(id);
        BookViewModel bvm = buildBookViewModel(book);
        return bvm;
    }

    public List<BookViewModel> findAllBooks() {
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        List<Book> books = bookDao.getAllBooks();

        for (Book book: books) {
            bookViewModelList.add(buildBookViewModel(book));
        }
        return bookViewModelList;
    }

    public void updateBook(BookViewModel bvm){
        //Update book
        Book book = new Book();
        book.setBookId(bvm.getBookId());
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());
        bookDao.updateBook(book);
    }

    public void removeBook(int id){
        //delete book
        bookDao.deleteBook(id);

    }

    private BookViewModel buildBookViewModel(Book book){
        BookViewModel bvm = new BookViewModel();
        //Build model with book
        bvm.setBookId(book.getBookId());
        bvm.setAuthor(book.getAuthor());
        bvm.setTitle(book.getTitle());

        //retrieve notes placeholder

        return bvm;
    }


    /**************************************************************
     Note API
     **************************************************************/

    public Note saveNote(Note note) {
        //Note Client service - Message Queue
        NoteEntry msg = new NoteEntry(note.getBookId(), note.getNote());
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("Message Sent");

       //How to get back note with database-inserted ID
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
        //Note Client Service - Message Queue note
        NoteEntry msg = new NoteEntry(note.getBookId(), note.getNote());
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("Message Sent");
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
