package com.ziyad.libraryspringproject.service;

import com.ziyad.libraryspringproject.domain.dto.AuthorRequest;
import com.ziyad.libraryspringproject.domain.dto.AuthorResponse;

public interface AuthorService{

    AuthorResponse createAuthor(AuthorRequest request);

    AuthorResponse findAuthorById(Long id);

    AuthorResponse updateAuthor(Long id, AuthorRequest request);

    void deleteAuthor(Long id);
}
