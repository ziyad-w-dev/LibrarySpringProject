package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.AuthorRequest;
import com.ziyad.libraryspringproject.domain.dto.AuthorResponse;
import com.ziyad.libraryspringproject.domain.dto.BookRequest;
import com.ziyad.libraryspringproject.domain.entity.Author;
import com.ziyad.libraryspringproject.exceptions.AuthorHasBooksException;
import com.ziyad.libraryspringproject.exceptions.AuthorNotFoundException;
import com.ziyad.libraryspringproject.mapper.AuthorMapper;
import com.ziyad.libraryspringproject.repository.AuthorRepository;
import com.ziyad.libraryspringproject.repository.BookRepository;
import com.ziyad.libraryspringproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    @Transactional
    @Override
    public AuthorResponse createAuthor(AuthorRequest request){
        Author authorEntity = authorMapper.toEntity(request);
        Author savedAuthor = authorRepository.save(authorEntity);
        AuthorResponse authorResponse = authorMapper.toDto(savedAuthor);

        return authorResponse;
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorResponse findAuthorById(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found With id: " + id));
        AuthorResponse authorResponse = authorMapper.toDto(author);

        return authorResponse;
    }

    @Transactional
    @Override
    public AuthorResponse AuthorUpdate(Long id, AuthorRequest request){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found With id: "+ id));
        author.setName(request.getName());
        Author savedAuthor = authorRepository.save(author);
        AuthorResponse authorResponse = authorMapper.toDto(savedAuthor);

        return authorResponse;
    }

    @Transactional
    @Override
    public void deleteAuthor(Long id){
        if(!authorRepository.existsById(id)){
            throw new AuthorNotFoundException("Author Not Found With id: "+id);
        }
        if(bookRepository.existsByAuthorId(id)){
            throw new AuthorHasBooksException("Author have books");
        }
        authorRepository.deleteById(id);
    }


}
