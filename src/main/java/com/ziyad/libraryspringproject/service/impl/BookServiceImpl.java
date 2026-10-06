package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.BookRequest;
import com.ziyad.libraryspringproject.domain.dto.BookResponse;
import com.ziyad.libraryspringproject.domain.dto.PartialUpdateBookRequest;
import com.ziyad.libraryspringproject.domain.entity.Author;
import com.ziyad.libraryspringproject.domain.entity.Book;
import com.ziyad.libraryspringproject.exceptions.AuthorNotFoundException;
import com.ziyad.libraryspringproject.exceptions.BookNotFoundException;
import com.ziyad.libraryspringproject.mapper.BookMapper;
import com.ziyad.libraryspringproject.repository.AuthorRepository;
import com.ziyad.libraryspringproject.repository.BookRepository;
import com.ziyad.libraryspringproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;


    @Transactional
    @Override
    public BookResponse createBook(BookRequest bookRequest){
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + bookRequest.getAuthorId()));
        Book bookEntity = bookMapper.toEntity(bookRequest, author);
        Book savedBook =  bookRepository.save(bookEntity);
        BookResponse bookResponse = bookMapper.toDto(savedBook);
        return bookResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponse findByBookId(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id ));
        BookResponse bookResponse = bookMapper.toDto(book);

        return bookResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookResponse> findByBookNameContaining(String contain){
        List<BookResponse> bookList = bookRepository.findByNameContaining(contain).stream()
                .map(bookMapper::toDto)
                .toList();
        return bookList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookResponse> findByBookPagesBetween(int from, int to){
        List<BookResponse> bookList = bookRepository.findByPagesBetween(from, to).stream()
                .map(bookMapper::toDto)
                .toList();

        return bookList;
    }

    @Transactional
    @Override
    public BookResponse partialUpdateBook(Long id,PartialUpdateBookRequest request) {
        Book realBook  = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        if(request.getName() != null){
            realBook.setName(request.getName());
        }
        if(request.getPages() != null){
            realBook.setPages(request.getPages());
        }
        if (request.getAuthorId() != null){
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + request.getAuthorId()));
            realBook.setAuthor(author);
        }
        Book savedBook = bookRepository.save(realBook);
        BookResponse bookResponse = bookMapper.toDto(savedBook);
        return bookResponse;
    }

    @Transactional
    @Override
    public BookResponse fullBookUpdate(Long id, BookRequest bookRequest){
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + bookRequest.getAuthorId()));
        Book realBook = bookMapper.toEntity(bookRequest, author);
        realBook.setId(id);
        Book savedBook = bookRepository.save(realBook);
        BookResponse bookResponse = bookMapper.toDto(savedBook);

        return bookResponse;
    }

    @Transactional
    @Override
    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
