package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.CreateBookRequest;
import com.ziyad.libraryspringproject.domain.entity.Author;
import com.ziyad.libraryspringproject.domain.entity.Book;
import com.ziyad.libraryspringproject.mapper.BookMapper;
import com.ziyad.libraryspringproject.repository.AuthorRepository;
import com.ziyad.libraryspringproject.repository.BookRepository;
import com.ziyad.libraryspringproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;


    @Override
    public Long createBook(CreateBookRequest createBookRequest){
        Author author = authorRepository.findById(createBookRequest.getAuthorId())
                .orElseThrow();
        Book bookEntity = bookMapper.toEntity(createBookRequest, author);
        Book savedBook =  bookRepository.save(bookEntity);
        return savedBook.getId();
    }

    @Override
    public Book findByBookId(Long id){
        return bookRepository.findById(id).
                orElseThrow();
    }

    @Override
    public List<Book> findByBookNameContaining(String contain){
        return bookRepository.findByNameContaining(contain);
    }

    @Override
    public List<Book> findByBookPagesBetween(int from, int to){
        return bookRepository.findByPagesBetween(from, to);
    }

}
