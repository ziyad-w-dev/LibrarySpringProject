package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.dto.CreateBookRequest;
import com.ziyad.libraryspringproject.domain.entity.Book;

import java.util.List;


public interface BookService {



    Long createBook(CreateBookRequest createBookRequest);

    Book findByBookId(Long id);

    List<Book> findByBookNameContaining(String contain);

    List<Book> findByBookPagesBetween(int from, int to);


}
