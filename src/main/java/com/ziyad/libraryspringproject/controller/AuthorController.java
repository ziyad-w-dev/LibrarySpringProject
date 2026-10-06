package com.ziyad.libraryspringproject.controller;

import com.ziyad.libraryspringproject.domain.dto.AuthorRequest;
import com.ziyad.libraryspringproject.domain.dto.AuthorResponse;
import com.ziyad.libraryspringproject.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@Valid @RequestBody AuthorRequest request){
        AuthorResponse response = authorService.createAuthor(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable Long id){
        AuthorResponse response = authorService.findAuthorById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequest request){

        AuthorResponse response = authorService.updateAuthor(id,request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);

        return ResponseEntity.noContent().build();
    }
}
