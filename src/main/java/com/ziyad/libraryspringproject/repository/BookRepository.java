package com.ziyad.libraryspringproject.repository;

import com.ziyad.libraryspringproject.domain.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByBookPagesBetween(int from, int to);
    Book findByBookNameContaining(String contain);

}
