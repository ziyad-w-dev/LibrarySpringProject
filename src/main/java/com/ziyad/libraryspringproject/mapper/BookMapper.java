package com.ziyad.libraryspringproject.mapper;

import com.ziyad.libraryspringproject.domain.dto.BookRequest;
import com.ziyad.libraryspringproject.domain.dto.BookResponse;
import com.ziyad.libraryspringproject.domain.entity.Author;
import com.ziyad.libraryspringproject.domain.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequest bookRequest, Author author){
        Book bookEntity = new Book();
        bookEntity.setName(bookRequest.getName());
        bookEntity.setPages(bookRequest.getPages());
        bookEntity.setAuthor(author);
        return bookEntity;
    }

    public BookResponse toDto(Book book){
           BookResponse bookResponse = BookResponse.builder()
                   .name(book.getName())
                   .id(book.getId())
                   .pages(book.getPages())
                   .authorName(book.getAuthor().getName())
                   .build();

           return bookResponse;
    }

}
