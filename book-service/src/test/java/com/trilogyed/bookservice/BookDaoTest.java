package com.trilogyed.bookservice;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {
    @Autowired
    protected BookDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Book> mList = dao.getAllBooks();

        mList.stream()
                .forEach(book -> dao.deleteBook(book.getBookId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteBook() {
        Book book = new Book();
        book.setTitle("Meditation as Medication");
        book.setAuthor("Sant Rajinder Singh Ji Maharaj");

        book = dao.addBook(book);
        Book book1 = dao.getBook(book.getBookId());

        assertEquals(book1, book);

        dao.deleteBook(book.getBookId());
        book1 = dao.getBook(book.getBookId());
        assertNull(book1);
    }

    @Test
    public void getAllBooks() {

        Book book = new Book();
        book.setTitle("Meditation as Medication");
        book.setAuthor("Sant Rajinder Singh Ji Maharaj");

        book = dao.addBook(book);

        book = new Book();
        book.setTitle("Daya Ke Phool");
        book.setAuthor("Sant Darshan Singh Ji Maharaj");

        dao.updateBook(book);

        List<Book> bList = dao.getAllBooks();
        assertEquals(2, bList.size());
    }

    @Test
    public void updateBook() {

        Book book = new Book();
        book.setTitle("Meditation as Medication");
        book.setAuthor("Sant Rajinder Singh Ji Maharaj");

        book = dao.addBook(book);

        book = new Book();
        book.setTitle("Daya Ke Phool");
        book.setAuthor("Sant Darshan Singh Ji Maharaj");

        dao.updateBook(book);

        Book book1 = dao.getBook(book.getBookId());

        assertEquals(book1, book);
    }
}
