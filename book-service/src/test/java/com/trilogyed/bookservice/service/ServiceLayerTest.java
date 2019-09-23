package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.util.feign.NoteServiceClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.Assert.*;

public class ServiceLayerTest {

    ServiceLayer service;
    BookDao bookDao;
    NoteServiceClient noteClient;
    RabbitTemplate rabbit;

    @Before
    public void setUp(){
        setUp
        service = new ServiceLayer(bookDao, noteClient, rabbit);
    }

    @Test
    public void addBook() {
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