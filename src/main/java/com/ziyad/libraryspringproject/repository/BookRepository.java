package com.ziyad.libraryspringproject.repository;

import com.ziyad.libraryspringproject.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByPagesBetween(int from, int to);
    List<Book> findByNameContaining(String contain);

}
