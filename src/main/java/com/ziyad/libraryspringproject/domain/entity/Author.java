package com.ziyad.libraryspringproject.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String authorName;

    @Column
    @OneToOne
    private User userId;


}
