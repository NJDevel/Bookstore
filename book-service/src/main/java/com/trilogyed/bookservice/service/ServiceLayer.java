package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.exception.BookNotFoundException;
import com.trilogyed.bookservice.exception.NoteNotFoundException;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
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
    private RabbitTemplate rabbitTemplate;
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    @Autowired
    private NoteServiceClient noteClient;

    @Autowired
    public ServiceLayer(BookDao bookDao, NoteServiceClient noteClient, RabbitTemplate rabbitTemplate){
        this.bookDao = bookDao;
        this.noteClient = noteClient;
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
        bvm.setBookId(book.getBookId());
        List<Note> newNotesList = new ArrayList<>();
        bvm.setNotes(newNotesList);

        return bvm;
    }

    public BookViewModel findBook(int id) {
        //Retrieve book

        Book book = bookDao.getBook(id);

        if(book == null) {
            throw new BookNotFoundException("The book ID you have entered does not exist in our database. Book ID: "
                    + id );
        }
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

        bvm.setNotes(noteClient.getNotesByBook(bvm.getBookId()));

        return bvm;
    }


    /**************************************************************
     Note API
     **************************************************************/

    public void saveNote(Note note) {

        //Note Client service - Message Queue
        NoteEntry msg = new NoteEntry(note.getBookId(), note.getNote());
        msg.setBookId(note.getBookId());
        msg.setNote(note.getNote());
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("Message Sent");

    }

    public BookViewModel findNote(int noteId){

        Note note = noteClient.getNote(noteId);
        if(note == null) {
            throw new NoteNotFoundException("The note ID you entered does not exist in our database. Note ID: " + noteId);
        }

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
        //Note Client Service - Message Queue note
        NoteEntry msg = new NoteEntry();
        msg.setBookId(note.getBookId());
        msg.setNote(note.getNote());
        msg.setNoteId(note.getNoteId());
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
        System.out.println("Message sent");
    }

    public void removeNote(int noteId){
        noteClient.deleteNote(noteId);
    }

    public BookViewModel findNotesByBook(int bookId){

        Book book = bookDao.getBook(bookId);
        if(book == null) {
            throw new BookNotFoundException("The book ID you have entered does not exist in our database. Book ID: "
                    + bookId );
        }

        List<Note> noteList = noteClient.getNotesByBook(bookId);

        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        bvm.setNotes(noteList);

        return bvm;
    }


}
