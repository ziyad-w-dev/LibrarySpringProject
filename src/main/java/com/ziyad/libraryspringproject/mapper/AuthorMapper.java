package com.ziyad.libraryspringproject.mapper;

import com.ziyad.libraryspringproject.domain.dto.AuthorRequest;
import com.ziyad.libraryspringproject.domain.dto.AuthorResponse;
import com.ziyad.libraryspringproject.domain.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequest request){
        Author authorEntity = new Author();
        authorEntity.setName(request.getName());

        return authorEntity;
    }

    public AuthorResponse toDto(Author authorEntity){
        AuthorResponse createAuthorResponse = AuthorResponse.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .build();

        return createAuthorResponse;
    }
}
