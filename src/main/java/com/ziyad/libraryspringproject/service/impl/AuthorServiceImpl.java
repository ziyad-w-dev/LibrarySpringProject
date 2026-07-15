package com.ziyad.libraryspringproject.service.impl;

import com.ziyad.libraryspringproject.domain.dto.AuthorRequest;
import com.ziyad.libraryspringproject.domain.dto.AuthorResponse;
import com.ziyad.libraryspringproject.domain.entity.Author;
import com.ziyad.libraryspringproject.exceptions.AuthorNotFoundException;
import com.ziyad.libraryspringproject.mapper.AuthorMapper;
import com.ziyad.libraryspringproject.repository.AuthorRepository;
import com.ziyad.libraryspringproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponse createAuthor(AuthorRequest request){
        Author authorEntity = authorMapper.toEntity(request);
        Author savedAuthor = authorRepository.save(authorEntity);
        AuthorResponse authorResponse = authorMapper.toDto(savedAuthor);

        return authorResponse;
    }

    @Override
    public Author findAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found With id: " + id));
    }

    @Override
    public AuthorResponse AuthorUpdate(Long id, AuthorRequest request){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found With id: "+ id));
        author.setName(request.getName());
        Author savedAuthor = authorRepository.save(author);
        AuthorResponse authorResponse = authorMapper.toDto(savedAuthor);

        return authorResponse;
    }

    @Override
    public void deleteAuthor(Long id){
        if(!authorRepository.existsById(id)){
            throw new AuthorNotFoundException("Author Not Found With id: "+id);
        }
        authorRepository.deleteById(id);
    }


}
