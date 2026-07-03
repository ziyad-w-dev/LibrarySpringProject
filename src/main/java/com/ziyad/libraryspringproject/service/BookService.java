package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.entity.Book;
import com.ziyad.libraryspringproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class BookService {


    private final BookRepository bookRepository;

    public Book findBookById(Long id){
        return bookRepository.findById(id).
                orElseThrow();
    }
}
