package com.sha.springbootbooksat333.service;

import com.sha.springbootbooksat333.model.Book;

import java.util.List;

public interface IBookService {
    //*************************************************************************************
    Book save(Book book);

    //********************************************************************************************
    void deleteBook(Long id);

    List<Book> findAllBooks();

    //********************************************************************************************

}
