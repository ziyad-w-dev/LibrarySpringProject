package com.ziyad.libraryspringproject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int pages;

    @ManyToOne(optional = false) // this Hibernate hint to make this field not null before going to DB
    @JoinColumn(name = "author_id", nullable = false) // to make it NOTNULL in DB level
    private Author author;

}
