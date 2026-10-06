package com.ziyad.libraryspringproject.repository;

import com.ziyad.libraryspringproject.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


}
