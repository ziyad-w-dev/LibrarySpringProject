package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.dto.AuthorRequest;
import com.ziyad.libraryspringproject.domain.dto.AuthorResponse;
import com.ziyad.libraryspringproject.domain.entity.Author;

public interface AuthorService{

    AuthorResponse createAuthor(AuthorRequest request);

    AuthorResponse findAuthorById(Long id);

    AuthorResponse AuthorUpdate(Long id, AuthorRequest request);

    void deleteAuthor(Long id);
}
