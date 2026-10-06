package com.ziyad.libraryspringproject.controller;

import com.ziyad.libraryspringproject.domain.dto.BookRequest;
import com.ziyad.libraryspringproject.domain.dto.BookResponse;
import com.ziyad.libraryspringproject.domain.dto.PartialUpdateBookRequest;
import com.ziyad.libraryspringproject.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.createBook(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        BookResponse response = bookService.findByBookId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<BookResponse>> getBookByName(@RequestParam String name) {
        List<BookResponse> response = bookService.findByBookNameContaining(name);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/pages")
    public ResponseEntity<List<BookResponse>> getBookByPages(
            @RequestParam int from,
            @RequestParam int to) {
        List<BookResponse> responses = bookService.findByBookPagesBetween(from, to);

        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/partial/{id}")
    public ResponseEntity<BookResponse> partialUpdate(
            @PathVariable Long id,
            @Valid @RequestBody PartialUpdateBookRequest request) {
        BookResponse response = bookService.partialUpdateBook(id, request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/full/{id}")
    public ResponseEntity<BookResponse> fullUpdate(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {
        BookResponse response = bookService.fullBookUpdate(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
