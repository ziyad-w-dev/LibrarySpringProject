package com.ziyad.libraryspringproject.mapper;

import com.ziyad.libraryspringproject.domain.dto.CreateBookRequest;
import com.ziyad.libraryspringproject.domain.dto.CreateBookResponse;
import com.ziyad.libraryspringproject.domain.entity.Author;
import com.ziyad.libraryspringproject.domain.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(CreateBookRequest createBookRequest, Author author){
        Book bookEntity = new Book();
        bookEntity.setName(createBookRequest.getName());
        bookEntity.setPages(createBookRequest.getPages());
        bookEntity.setAuthor(author);
        return bookEntity;
    }

    public CreateBookResponse toDto(Book book){
           CreateBookResponse bookResponse = CreateBookResponse.builder()
                   .name(book.getName())
                   .id(book.getId())
                   .pages(book.getPages())
                   .authorName(book.getAuthor().getName())
                   .build();

           return bookResponse;
    }
}
