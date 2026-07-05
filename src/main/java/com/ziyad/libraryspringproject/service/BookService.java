package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.entity.Book;
import com.ziyad.libraryspringproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BookService {


    private final BookRepository bookRepository;


    public void createBook(){

    }

    public Book findByBookId(Long id){
        return bookRepository.findById(id).
                orElseThrow();
    }

    public List<Book> findByBookNameContaining(String contain){
        return bookRepository.findByNameContaining(contain);
    }

    public List<Book> findByBookPagesBetween(int from, int to){
        return bookRepository.findByPagesBetween(from, to);
    }



}
