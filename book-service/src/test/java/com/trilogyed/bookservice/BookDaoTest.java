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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest implements BookDao {
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

    public BookDaoTest() {
        super();
    }

    @Override
    public Book getBook(int bookId) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(int bookId) {

    }
}
