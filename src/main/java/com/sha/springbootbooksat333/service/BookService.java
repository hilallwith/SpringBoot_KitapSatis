package com.sha.springbootbooksat333.service;

import com.sha.springbootbooksat333.model.Book;
import com.sha.springbootbooksat333.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService implements IBookService{


    @Autowired
    private IBookRepository bookRepository;
//*************************************************************************************
    @Override
    public Book save(Book book){         //kitap kaydediyoruz
        book.setCreateTime(LocalDateTime.now());        //o anki tarihe göre kaydet diyoruz
        return bookRepository.save(book);

    }

    //********************************************************************************************
     @Override
    public void deleteBook(Long id){         //id ye göre delete yap geriye birşey dönmiycek
        bookRepository.deleteById(id);

    }

    //*********************************************************************************************

    @Override
    public List<Book> findAllBooks(){           //kitapları listele
        return (List<Book>)bookRepository.findAll();        //Hata düzeldi (Lşst<Book>) türünde olsun diyoruz
    }
//******************************************************************************************************












}
