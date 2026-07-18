package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.BookRequest;
import com.ziyad.libraryspringproject.domain.dto.BookResponse;
import com.ziyad.libraryspringproject.domain.entity.Author;
import com.ziyad.libraryspringproject.domain.entity.Book;
import com.ziyad.libraryspringproject.exceptions.AuthorNotFoundException;
import com.ziyad.libraryspringproject.exceptions.BookNotFoundException;
import com.ziyad.libraryspringproject.mapper.BookMapper;
import com.ziyad.libraryspringproject.repository.AuthorRepository;
import com.ziyad.libraryspringproject.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;



@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Test
    void createBookIfAuthorExist() {
        // Arrange
        Author author = new Author();
        BookRequest fake = BookRequest.builder()
                .name("The Book") .pages(100) .authorId(1L) .build();
        Book book = new Book();
        BookResponse bookResponse = BookResponse.builder()
                .id(1L).name("The Book").pages(100).authorName("author").build();

        when(bookRepository.save(book)).thenReturn(book);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookMapper.toDto(book)).thenReturn(bookResponse);
        when(bookMapper.toEntity(fake,author)).thenReturn(book);

        // Act
        BookResponse result = bookServiceImpl.createBook(fake);

        // Assert
        assertEquals(bookResponse, result);
    }
    @Test
    void createBookIfAuthorDontExist(){
        // Arrange
        BookRequest bookRequest = new BookRequest();
        bookRequest.setAuthorId(1l);
        // stubbing
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(AuthorNotFoundException.class, () -> bookServiceImpl.createBook(bookRequest));
    }
    @Test
    void findBookIfExist() {
        // Arrange
        Book bookEntity = new Book();
        BookResponse bookResponse = new BookResponse();
            // stubbing
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toDto(any())).thenReturn(bookResponse);
        // Act
        BookResponse result = bookServiceImpl.findByBookId(1L);
        // Assert
        assertEquals(bookResponse,result);
    }
    @Test
    void findBookIfNotExist() {
        // stubbing
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        // Assert & Act
        assertThrows(BookNotFoundException.class, () -> bookServiceImpl.findByBookId(1L));
    }


}