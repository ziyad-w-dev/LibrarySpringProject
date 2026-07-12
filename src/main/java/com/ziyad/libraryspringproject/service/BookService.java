package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.dto.BookRequest;
import com.ziyad.libraryspringproject.domain.dto.BookResponse;
import com.ziyad.libraryspringproject.domain.dto.PartialUpdateBookRequest;
import com.ziyad.libraryspringproject.domain.entity.Book;

import java.util.List;


public interface BookService {



    BookResponse createBook(BookRequest bookRequest);

    Book findByBookId(Long id);

    List<Book> findByBookNameContaining(String contain);

    List<Book> findByBookPagesBetween(int from, int to);

    BookResponse partialUpdateBook (Long id, PartialUpdateBookRequest request);

    BookResponse fullBookUpdate(Long id, BookRequest bookRequest);

    void deleteBook(Long id);


}
