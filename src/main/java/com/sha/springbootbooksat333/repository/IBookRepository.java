package com.sha.springbootbooksat333.repository;

import com.sha.springbootbooksat333.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepository extends CrudRepository<Book,Long> {
}
